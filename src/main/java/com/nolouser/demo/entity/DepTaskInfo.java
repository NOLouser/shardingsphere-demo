package com.nolouser.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * dep任务表
 * </p>
 *
 * @author nolouser
 * @since 2020-12-16
 */
@TableName("dep_task_info")
public class DepTaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 无业务意义主键
     */
    @TableId("id_")
    private String id;

    /**
     * SAP回传地址
     */
    @TableField("ext_api_url_")
    private String extApiUrl;

    /**
     * 业务系统任务ID
     */
    @TableField("ext_task_id_")
    private String extTaskId;

    /**
     * 来自业务系统任务类
     */
    @TableField("ext_task_type_")
    private String extTaskType;

    /**
     * 来自业务系统参数
     */
    @TableField("ext_task_data_")
    private String extTaskData;

    /**
     * 客户ID
     */
    @TableField("ext_kh_id_")
    private String extKhId;

    /**
     * 客户名称
     */
    @TableField("ext_kh_name_")
    private String extKhName;

    /**
     * 纳税人识别号
     */
    @TableField("ext_kh_nsrsbh_")
    private String extKhNsrsbh;

    @TableField("ext_cellphone_")
    private String extCellphone;

    /**
     * 税务机关 code
     */
    @TableField("ext_swjg_code_")
    private String extSwjgCode;

    @TableField("ext_bb_code_")
    private String extBbCode;

    /**
     * 指标ID
     */
    @TableField("ext_zb_id_")
    private String extZbId;

    /**
     * 来自业务系统创建时间
     */
    @TableField("ext_create_date_")
    private LocalDateTime extCreateDate;

    /**
     * dep的配置信息
     */
    @TableField("dep_data_")
    private String depData;

    /**
     * 省份
     */
    @TableField("province_")
    private String province;

    /**
     * 任务类型
     */
    @TableField("task_name_")
    private String taskName;

    /**
     * 任务状态：0未执行、1执行中、2成功、3失败、4挂起
     */
    @TableField("status_")
    private Integer status;

    /**
     * BOSS系统运行信息
     */
    @TableField("message_")
    private String message;

    /**
     * 渠道: RPA、SELENIUM、INTERFACE
     */
    @TableField("channel_")
    private String channel;

    /**
     * 批次号
     */
    @TableField("batch_no_")
    private String batchNo;

    /**
     * 0单个、1批量
     */
    @TableField("is_batch_")
    private Integer isBatch;

    /**
     * 当前任务是否正在登陆，0:否1:是
     */
    @TableField("is_login_")
    private Integer isLogin;

    /**
     * 是否挂起过，0:否;1:是
     */
    @TableField("is_hangup_")
    private Integer isHangup;

    /**
     * 运行次数
     */
    @TableField("run_times_")
    private Integer runTimes;

    /**
     * 当前任务最近一次开始执行事件
     */
    @TableField("run_date_")
    private LocalDateTime runDate;

    /**
     * 结束日期
     */
    @TableField("end_date_")
    private LocalDateTime endDate;

    /**
     * 创建人
     */
    @TableField("create_user_")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField("create_date_")
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @TableField("update_date_")
    private LocalDateTime updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getExtApiUrl() {
        return extApiUrl;
    }

    public void setExtApiUrl(String extApiUrl) {
        this.extApiUrl = extApiUrl;
    }
    public String getExtTaskId() {
        return extTaskId;
    }

    public void setExtTaskId(String extTaskId) {
        this.extTaskId = extTaskId;
    }
    public String getExtTaskType() {
        return extTaskType;
    }

    public void setExtTaskType(String extTaskType) {
        this.extTaskType = extTaskType;
    }
    public String getExtTaskData() {
        return extTaskData;
    }

    public void setExtTaskData(String extTaskData) {
        this.extTaskData = extTaskData;
    }
    public String getExtKhId() {
        return extKhId;
    }

    public void setExtKhId(String extKhId) {
        this.extKhId = extKhId;
    }
    public String getExtKhName() {
        return extKhName;
    }

    public void setExtKhName(String extKhName) {
        this.extKhName = extKhName;
    }
    public String getExtKhNsrsbh() {
        return extKhNsrsbh;
    }

    public void setExtKhNsrsbh(String extKhNsrsbh) {
        this.extKhNsrsbh = extKhNsrsbh;
    }
    public String getExtCellphone() {
        return extCellphone;
    }

    public void setExtCellphone(String extCellphone) {
        this.extCellphone = extCellphone;
    }
    public String getExtSwjgCode() {
        return extSwjgCode;
    }

    public void setExtSwjgCode(String extSwjgCode) {
        this.extSwjgCode = extSwjgCode;
    }
    public String getExtBbCode() {
        return extBbCode;
    }

    public void setExtBbCode(String extBbCode) {
        this.extBbCode = extBbCode;
    }
    public String getExtZbId() {
        return extZbId;
    }

    public void setExtZbId(String extZbId) {
        this.extZbId = extZbId;
    }
    public LocalDateTime getExtCreateDate() {
        return extCreateDate;
    }

    public void setExtCreateDate(LocalDateTime extCreateDate) {
        this.extCreateDate = extCreateDate;
    }
    public String getDepData() {
        return depData;
    }

    public void setDepData(String depData) {
        this.depData = depData;
    }
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public Integer getIsBatch() {
        return isBatch;
    }

    public void setIsBatch(Integer isBatch) {
        this.isBatch = isBatch;
    }
    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }
    public Integer getIsHangup() {
        return isHangup;
    }

    public void setIsHangup(Integer isHangup) {
        this.isHangup = isHangup;
    }
    public Integer getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Integer runTimes) {
        this.runTimes = runTimes;
    }
    public LocalDateTime getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDateTime runDate) {
        this.runDate = runDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "DepTaskInfo{" +
            "id=" + id +
            ", extApiUrl=" + extApiUrl +
            ", extTaskId=" + extTaskId +
            ", extTaskType=" + extTaskType +
            ", extTaskData=" + extTaskData +
            ", extKhId=" + extKhId +
            ", extKhName=" + extKhName +
            ", extKhNsrsbh=" + extKhNsrsbh +
            ", extCellphone=" + extCellphone +
            ", extSwjgCode=" + extSwjgCode +
            ", extBbCode=" + extBbCode +
            ", extZbId=" + extZbId +
            ", extCreateDate=" + extCreateDate +
            ", depData=" + depData +
            ", province=" + province +
            ", taskName=" + taskName +
            ", status=" + status +
            ", message=" + message +
            ", channel=" + channel +
            ", batchNo=" + batchNo +
            ", isBatch=" + isBatch +
            ", isLogin=" + isLogin +
            ", isHangup=" + isHangup +
            ", runTimes=" + runTimes +
            ", runDate=" + runDate +
            ", endDate=" + endDate +
            ", createUser=" + createUser +
            ", createDate=" + createDate +
            ", updateDate=" + updateDate +
        "}";
    }
}
