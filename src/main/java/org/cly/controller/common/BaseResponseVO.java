package org.cly.controller.common;

import lombok.Data;

@Data
public final class BaseResponseVO<M> {

    private BaseResponseVO(){}

    //返回状态【0-成功，1-业务失败，999-系统异常】
    private int status;
    private String msg;
    //数据实体
    M data;
    //图片前缀
    private String imgPre;

    //分页使用
    private Long nowPage;
    private Long totalPage;

    public static <M> BaseResponseVO success(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        return baseResponseVO;
    }

    public static<M> BaseResponseVO success(String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static <M> BaseResponseVO success(M data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(0);
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    public static<M> BaseResponseVO success(long nowPage,long totalPage,M m){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setTotalPage(totalPage);
        responseVO.setNowPage(nowPage);

        return responseVO;
    }

    public static<M> BaseResponseVO success(long nowPage,long totalPage,String imgPre,M m){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);
        responseVO.setTotalPage(totalPage);
        responseVO.setNowPage(nowPage);

        return responseVO;
    }

    public static<M> BaseResponseVO success(String imgPre,M m){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);

        return responseVO;
    }

    public static<M> BaseResponseVO noLogin(){
        BaseResponseVO responseVO = new BaseResponseVO();
        responseVO.setStatus(700);
        responseVO.setMsg("用户需要登陆");

        return responseVO;
    }


    public static<M> BaseResponseVO serviceFailed(String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(1);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }

    public static<M> BaseResponseVO serviceFailed(String msg,M data){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(1);
        baseResponseVO.setMsg(msg);
        baseResponseVO.setData(data);
        return baseResponseVO;
    }


    public static<M> BaseResponseVO serviceFailed(int status,String msg){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(status);
        baseResponseVO.setMsg(msg);
        return baseResponseVO;
    }


    public static<M> BaseResponseVO systemError(){
        BaseResponseVO baseResponseVO = new BaseResponseVO();
        baseResponseVO.setStatus(999);
        baseResponseVO.setMsg("系统异常，请联系管理员！");
        return baseResponseVO;
    }
}
