package com.portfolio.util;

/**
 * The type Script.
 */
public class Script {
    /**
     * @param message
     * @return string
     * @methodName : alertAndRedirect
     * @author : rulethecode9060
     * @date : 2023.07.13
     * @description : 설정한 message로 alert창 출력 후 이전 주소로 리다이렉트 요청
     */
    public static String back(String message){
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+message+"');");
            sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }
}
