package utils;

/**
 * author：zslz-001
 * Date:2018/9/20
 */
public class ErrorCodeUtils {
    /**
     * 翻译错误码
     * @param rtnCode
     * @param rtnMsg
     * @return
     */
    public static String checkResult(String rtnCode,String rtnMsg){
        String result="";
        if (rtnCode.equals("000001")) {//手机号码为空
            result = "账号为空";
        } else if (rtnCode.equals("000002")) {//密码为空
            result = "账号或密码错误";
        } else if (rtnCode.equals("000003")) {//验证码为空
            result = "验证码为空";
        } else if (rtnCode.equals("000004")) {//手机号码已存在
            result = "手机号码已存在";
        } else if (rtnCode.equals("000005")) {////验证码错误
            result = "验证码错误";
        } else if (rtnCode.equals("000006")) {//密码加密错误
            result = "密码错误";
        } else if (rtnCode.equals("000007")) {//用户不存在
            result = "账号不存在";
        } else if (rtnCode.equals("000008")) {//用户未登录
            result = "";
        } else if (rtnCode.equals("000009")) {//微信openid为空
            result = "";
        } else if (rtnCode.equals("000010")) {//微信openid已经关联其他用
            result = "";
        } else if (rtnCode.equals("000011")) {//用户已关联了微信帐号
            result = "";
        } else if (rtnCode.equals("000012")) {//微信帐号和关联用户不一致
            result = "";
        } else if (rtnCode.equals("000013")) {//暂无进行中的活动
            result = "";
        } else if (rtnCode.equals("000014")) {//暂无已过期的活动
            result = "";
        } else if (rtnCode.equals("000015")) {//暂无将要进行的活动
            result = "";
        } else if (rtnCode.equals("000016")) {//字典编码为空
            result = "";
        } else if (rtnCode.equals("000017")) {//订单ID为空
            result = "";
        } else if (rtnCode.equals("000018")) {//省份为空
            result = "您选择的省份为空";
        } else if (rtnCode.equals("000019")) {//市为空
            result = "";
        } else if (rtnCode.equals("000020")) {//区为空
            result = "";
        } else if (rtnCode.equals("000021")) {//商圈为空
            result = "";
        } else if (rtnCode.equals("000022")) {//小区为空
            result = "";
        } else if (rtnCode.equals("000023")) {//楼号为空
            result = "";
        } else if (rtnCode.equals("000024")) {//单元为空
            result = "";
        } else if (rtnCode.equals("000025")) {//房间号为空
            result = "";
        } else if (rtnCode.equals("000026")) {//用户暂未设置地址
            result = "请先设置客户的地址";
        } else if (rtnCode.equals("000027")) {//小区标识为空
            result = "";
        } else if (rtnCode.equals("000028")) {//回收类型为空
            result = "";
        } else if (rtnCode.equals("000029")) {//商品编码为空
            result = "";
        } else if (rtnCode.equals("000030")) {//上门日期为空
            result = "";
        } else if (rtnCode.equals("000031")) {//上门时间为空
            result = "";
        } else if (rtnCode.equals("000032")) {//用户地址为空
            result = "请设置客户地址";
        } else if (rtnCode.equals("000033")) {//意见反馈内容为空
            result = "";
        } else if (rtnCode.equals("000034")) {//商品分类为空
            result = "";
        } else if (rtnCode.equals("000035")) {//回收人员ID为空
            result = "";
        } else if (rtnCode.equals("000036")) {//查询不到订单为空
            result = "";
        } else if (rtnCode.equals("000037")) {//订单已被接收
            result = "";
        } else if (rtnCode.equals("000038")) {//订单与登录人员不匹配
            result = "";
        } else if (rtnCode.equals("000039")) {//回收人员id为空
            result = "";
        } else if (rtnCode.equals("000040")) {//物品编号为空
            result = "";
        } else if (rtnCode.equals("000041")) {//物品名称为空
            result = "";
        } else if (rtnCode.equals("000042")) {//物品单价为空
            result = "";
        } else if (rtnCode.equals("000043")) {//物品数量为空
            result = "";
        } else if (rtnCode.equals("000044")) {//支付宝帐号为空
            result = "";
        } else if (rtnCode.equals("000045")) {//结算方式为空
            result = "";
        } else if (rtnCode.equals("000046")) {//提现金额为空
            result = "";
        } else if (rtnCode.equals("000047")) {//提现金额大于余额
            result = "";
        } else if (rtnCode.equals("000048")) {//用户账户为空
            result = "";
        } else if (rtnCode.equals("000049")) {//开始或结束回收时间为空
            result = "";
        } else if (rtnCode.equals("000050")) {//开始回收经度为空
            result = "";
        } else if (rtnCode.equals("000051")) {//开始回收纬度为空
            result = "";
        } else if (rtnCode.equals("000052")) {//取消订单理由为空
            result = "";
        } else if (rtnCode.equals("000053")) {//目录编号为空
            result = "";
        } else if (rtnCode.equals("000054")) {//用户类型为空
            result = "";
        } else if (rtnCode.equals("000055")) {//用户类型名称为空
            result = "";
        } else if (rtnCode.equals("000056")) {//方法每天调用一次
            result = "";
        } else if (rtnCode.equals("000057")) {//订单统计维度
            result = "";
        } else if (rtnCode.equals("000058")) {//订单已提交
            result = "订单已结算";
        } else if (rtnCode.equals("000059")) {//订单值和实际值误差过大
            result = "";
        } else if (rtnCode.equals("000060")) {//订单值或者实际值为空
            result = "";
        } else if (rtnCode.equals("000061")) {//入库批准失败
            result = "";
        } else if (rtnCode.equals("000062")) {//创建入库单失败
            result = "";
        } else if (rtnCode.equals("000063")) {//应用类型为空
            result = "";
        } else if (rtnCode.equals("000064")) {//版本类型为空
            result = "";
        } else if (rtnCode.equals("000065")) {//获取应用版本失败
            result = "";
        } else if (rtnCode.equals("000066")) {//订单ID为空
            result = "";
        } else if (rtnCode.equals("000067")) {//无法找到订单
            result = "";
        } else if (rtnCode.equals("000068")) {//用户ID为空
            result = "";
        } else if (rtnCode.equals("000069")) {//物品单位为空
            result = "";
        } else if (rtnCode.equals("000070")) {//活动状态为空
            result = "";
        } else if (rtnCode.equals("000071")) {//用户下单渠道为空
            result = "";
        } else if (rtnCode.equals("000077")) {//订单状态为空
            result = "";
        } else if (rtnCode.equals("000078")) {//回收员id为空
            result = "";
        } else if (rtnCode.equals("000086")) {//代码类型为空
            result = "";
        } else if (rtnCode.equals("000092")) {//司机id为空
            result = "";
        } else if (rtnCode.equals("000093")) {//车牌号为空
            result = "";
        } else if (rtnCode.equals("000095")) {//图片名称为空
            result = "";
        } else if (rtnCode.equals("000098")) {//车辆订单任务id为空
            result = "";
        } else if (rtnCode.equals("000099")) {//出车类型为空
            result = "";
        } else if (rtnCode.equals("000102")) {//客户回收类型为空
            result = "";
        } else if (rtnCode.equals("000107")) {//客户姓名为空
            result = "";
        } else if (rtnCode.equals("000108")) {//回收渠道订单
            result = "";
        } else if (rtnCode.equals("000109")) {//验纸员为空
            result = "";
        } else if (rtnCode.equals("000114")) {//是否需要验纸标识为空
            result = "请选择是否需要验纸";
        } else if (rtnCode.equals("000129")) {//该车辆入库数据已提交
            result = "该车辆入库数据已提交";
        }else if (rtnCode.equals("000161")) {//纸板已经录入完成
            result = "";
        } else if (rtnCode.equals("000162")) {//其他物品已经录入完成
            result = "";
        } else if (rtnCode.equals("000163")) {//该车辆已经分拣完成
            result = "";
        } else if (rtnCode.equals("000164")) {//该车辆只有纸板
            result = "该车辆只有纸板";
        } else if (rtnCode.equals("000165")) {//录入物品种类为空
            result = "请添加要录入的物品";
        } else if (rtnCode.equals("900902")) {//返回此值后，将提示用户需要重新登录
            result = "服务权限检查失败";
        } else if(rtnCode.equals("000145")){//英雄榜，统计标识为空
            result = "";
        }else if(rtnCode.equals("000144")){//回收中心为空
            result = "";
        }else if(rtnCode.equals("000149")){//签约/连锁用户日期选项为空
            result = "";
        }else if(rtnCode.equals("000150")){//查询日期为空
            result = "";
        }else if(rtnCode.equals("000148")){//消息范围为空
            result = "";
        }else if(rtnCode.equals("111111")){//网络请求失败
            result = "无法连接到服务器，请检查网络";
        }else if(rtnCode.equals("00000053")){//token 过期
            result = "请重新登陆";
        }else {
            result = rtnMsg;
        }
        return result;
    };
}
