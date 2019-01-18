package cn.itrip.beans.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by zezhong.shang on 17-5-9.
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ItripException extends Exception{
    @NonNull
    //异常码
    private String exceptionCode;
    //异常信息
    @NonNull
    private String message;

    public ItripException(String exceptionCode) {
        super();
        this.exceptionCode = exceptionCode;
    }

}
