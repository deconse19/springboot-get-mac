package com.weasle.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.weasle.test.entity.Device;
import com.weasle.test.service.DeviceService;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(HttpServletRequest request) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            if (mac != null) {  
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }

                String macAddress = sb.toString();
                String clientIp = ip.getHostAddress();

                // Save the device information
                String result = deviceService.registerDevice(clientIp, macAddress);
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(404).body("MAC address not found.");
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error retrieving MAC address.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);  
    }

    // Extract MAC address from the client IP address
    // private String getMacAddress(String ip) {
    // String macAddress = null;
    // try {
    // Process process = Runtime.getRuntime().exec("arp -a " + ip);
    // BufferedReader reader = new BufferedReader(new
    // InputStreamReader(process.getInputStream()));
    // String line;
    // Pattern pattern = Pattern.compile("([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})");

    // while ((line = reader.readLine()) != null) {
    // Matcher matcher = pattern.matcher(line);
    // if (matcher.find()) {
    // macAddress = matcher.group();
    // break;
    // }
    // }
    // reader.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return macAddress;
    // }
    // }

}
