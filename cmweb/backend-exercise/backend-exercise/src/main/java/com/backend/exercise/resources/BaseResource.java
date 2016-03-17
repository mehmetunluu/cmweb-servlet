package com.backend.exercise.resources;

/**
 * Created by M.UNLU on 02.03.2016.
 */
public class BaseResource {

    public void checkQueryParameters(Object... parameters) {
        if(parameters.length % 3 !=0) {
            throw new NullPointerException("Parameters are not in correct form eg. (param,name,defaultValue)");
        }
        for (int i = 0; i+3<=parameters.length ; i+=3) {
            Object param = parameters[i];
            String name = parameters[i+1].toString();
            String defaultValue = parameters[i+2].toString();
            if((param == null )&& defaultValue !=null){
                try {
                    throw new Exception("An error excepted");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
