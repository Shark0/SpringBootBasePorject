package com.shark.base.controller.rest;



import com.shark.base.dto.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest")
public class RestController {

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public ResponseEntity get() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(1);
        responseEntity.setReturnMessage("get");
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/post")
    public ResponseEntity post() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(1);
        responseEntity.setReturnMessage("post");
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/put")
    public ResponseEntity put() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(1);
        responseEntity.setReturnMessage("put");
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete() {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setReturnCode(1);
        responseEntity.setReturnMessage("delete");
        return responseEntity;
    }

}
