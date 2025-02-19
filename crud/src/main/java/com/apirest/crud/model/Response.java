package com.apirest.crud.model;

public class Response {

    private Object data;
    private String mensage;


    public Response(Object data, String mensage) {
        this.data = data;
        this.mensage = mensage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
}
