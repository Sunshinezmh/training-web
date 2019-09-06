package com.dmsdbj.integral.training.entity.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Classname DelayTaskModel
 * @Auther ZMH
 * @Date 2019/7/28 11:06
 */

@Data
@ToString
@NoArgsConstructor
public class DelayTaskModel implements Serializable {

    //项目的名称
    private String projectName;

    private String realName;

    //项目的创建者ID
    private String userId;

    //项目的创建者
    private String openedBy;

    //项目的到期日期
    private Date deadLine;

    //项目的实际开始日期
    private Date realStarted;

    private List mentors;

}
