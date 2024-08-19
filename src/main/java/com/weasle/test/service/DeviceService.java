package com.weasle.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.weasle.test.entity.Device;
import com.weasle.test.repo.DeviceRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public String registerDevice(String ipAddress, String macAddress) {
        if (macAddress != null && !macAddress.isEmpty()) {
            Device device = new Device();
            device.setMacAddress(macAddress);
            device.setIpAddress(ipAddress);
            device.setRegisteredAt(LocalDateTime.now());

            deviceRepository.save(device);
            return "Device registered successfully";
        } else {
            return "MAC Address not found";
        }
    }

    // public String getMacAddress(String ip) {
    //     String macAddress = null;
    //     try {
    //         Process process = Runtime.getRuntime().exec("arp -a " + ip);
    //         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    //         String line;
    //         Pattern pattern = Pattern.compile("([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})");

    //         while ((line = reader.readLine()) != null) {
    //             Matcher matcher = pattern.matcher(line);
    //             if (matcher.find()) {
    //                 macAddress = matcher.group();
    //                 break;
    //             }
    //         }
    //         reader.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return macAddress;
    // }

    // Method to get all devices from the database
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}
