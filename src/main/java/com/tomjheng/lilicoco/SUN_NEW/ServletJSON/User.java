package com.tomjheng.lilicoco.SUN_NEW.ServletJSON;

public class User {
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User(String name, String pwd) {
        this.pwd = pwd;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", pwd=" + pwd + "]";
    }

}
