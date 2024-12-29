package com.okccc.eshop.model.entity.order;

import com.okccc.eshop.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2024/5/10 17:08:03
 * @Desc:
 */
@Schema(description = "订单实体类")
@Data
public class OrderInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "会员id")
	private Long userId;

	@Schema(description = "昵称")
	private String nickName;

	@Schema(description = "订单号")
	private String orderNo;

	@Schema(description = "使用的优惠券")
	private Long couponId;

	@Schema(description = "订单总额")
	private BigDecimal totalAmount;

	@Schema(description = "优惠券")
	private BigDecimal couponAmount;

	@Schema(description = "原价金额")
	private BigDecimal originalTotalAmount;

	@Schema(description = "运费")
	private BigDecimal feightFee;

	@Schema(description = "支付方式(1微信,2支付宝)")
	private Integer payType;

	@Schema(description = "订单状态(0待付款,1待发货,2已发货,3待收货,-1已取消)")
	private Integer orderStatus;

	@Schema(description = "收货人姓名")
	private String receiverName;

	@Schema(description = "收货人电话")
	private String receiverPhone;

	@Schema(description = "收货人地址标签")
	private String receiverTagName;

	@Schema(description = "省份/直辖市")
	private String receiverProvince;

	@Schema(description = "城市")
	private String receiverCity;

	@Schema(description = "区")
	private String receiverDistrict;

	@Schema(description = "详细地址")
	private String receiverAddress;

	@Schema(description = "支付时间")
	private Date paymentTime;

	@Schema(description = "发货时间")
	private Date deliveryTime;

	@Schema(description = "确认收货时间")
	private Date receiveTime;

	@Schema(description = "订单备注")
	private String remark;

	@Schema(description = "取消订单时间")
	private Date cancelTime;

	@Schema(description = "取消订单原因")
	private String cancelReason;

	@Schema(description = "订单明细列表(非数据库字段)")
	private List<OrderItem> orderItemList;

}