package com.nolouser.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author nolouser
 * @since 2020-12-22
 */
@TableName("t_order")
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    @TableField("user_id")
    private Integer userId;

    @TableField("address_id")
    private Long addressId;

    @TableField("status")
    private String status;

    @TableField("datetime")
    private String datetime;

    public TOrder() {
    }

    public TOrder(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(this.status.hashCode() + this.orderId);
    }

    @Override
    public String toString() {
        return "TOrder{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", addressId=" + addressId +
            ", status=" + status +
            ", datetime=" + datetime +
        "}";
    }
}
