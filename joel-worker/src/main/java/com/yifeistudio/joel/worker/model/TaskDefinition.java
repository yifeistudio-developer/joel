package com.yifeistudio.joel.worker.model;

import com.yifeistudio.joel.worker.model.enm.TaskType;
import lombok.Data;

/**
 * 任务抽象
 * @author yi
 * @since 2020/10/31-2:34 下午
 */
@Data
public class TaskDefinition {

    private TaskType type;


}
