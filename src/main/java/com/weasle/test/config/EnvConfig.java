package com.weasle.test.config;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class EnvConfig {

    private Dotenv dotenv;

    public EnvConfig(){
      dotenv = Dotenv.configure().load();
    }

    // public String getJwtSecret() {
    //     return dotenv.get("security.jwt.secret-key");
    // }

    // comment

    public String getSsId(){
        return dotenv.get("ssid");
    }
    public String getApmac(){
        return dotenv.get("apmac");
    }
    public String getVlanId(){
        return dotenv.get("vlanid");
    }
    public String getGwId(){
        return dotenv.get("gw_id");
    }
    public String getGwSn(){
        return dotenv.get("gw_sn");
    }
    public String getGwAdress(){
        return dotenv.get("gw_address");
    }
    public String getGwPort(){
        return dotenv.get("gw_port");
    }
    public String getUrl(){
        return dotenv.get("url");
    }

}
