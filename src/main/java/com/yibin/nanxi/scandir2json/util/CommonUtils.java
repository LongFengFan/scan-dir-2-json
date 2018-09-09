package com.yibin.nanxi.scandir2json.util;


import com.yibin.nanxi.scandir2json.vo.ResultVO;

/**
 * Created by LongFF on 2018/8/14
 */
public class CommonUtils {
    public static ResultVO success(Object obj){
        ResultVO resultVO = new ResultVO();
        return resultVO.setMessage("success").setResult(obj).setStatus("ok");
    }

    public static ResultVO error(String msg){
        ResultVO resultVO = new ResultVO();
        return resultVO.setMessage(msg).setStatus("error");
    }
}
