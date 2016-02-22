package gem.training3.enterprisenetwork.network.dto;

import gem.training3.enterprisenetwork.base.BaseDTO;

/**
 * Created by huylv on 22/02/2016.
 */
public class ResponseDTO extends BaseDTO{
    int statusCode;
    String message;
    Object returnObject;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public ResponseDTO(int statusCode, String message, Object returnObject) {

        this.statusCode = statusCode;
        this.message = message;
        this.returnObject = returnObject;
    }
}
