package com.shitouren.core.config.exception;


public class CloudException extends RuntimeException {

    public CloudException() {
    }

    public CloudException(String code, String info) {
		super();
		this.code = code;
		this.info = info;
	}

    public CloudException(String code, String info, Object data) {
        super();
        this.code = code;
        this.info = info;
        this.data = data == null ? "":data;
    }
    public CloudException(ExceptionConstant constant) {
        super();
        this.code = constant.getCode();
        this.info = constant.getMsg();
    }
    public CloudException(ExceptionConstant constant, Object data) {
        super();
        this.code = constant.getCode();
        this.info = constant.getMsg();
        this.data = data;
    }


	public CloudException(String info) {
		super();
		this.info = info;
	}

	private static final long serialVersionUID = 70526377718473430L;
	private String code;
	private String info;
	private Object data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        if(data == null){
            data =  new Object();
        }
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
