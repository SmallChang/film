package org.cly.controller.film.vo.request;

import lombok.Data;
import org.cly.controller.common.BaseVO;
import org.cly.controller.exception.ParamErrorException;

import java.io.Serializable;

@Data
public class DescribeFilmListReqVO extends BaseVO implements Serializable {

    private String showType="1";   // 判断待获取的电影类型
    private String sortId="1";
    private String catId="99";
    private String sourceId="99";
    private String yearId="99";
    private String nowPage="1";
    private String pageSize="18";


    @Override
    public void checkParam() throws ParamErrorException {
        // TODO
        // showType如果不是1，2，3里的任意一个，则直接抛出异常
        // sortId如果不是1，2，3里的任意一个，则直接抛出异常
    }

}
