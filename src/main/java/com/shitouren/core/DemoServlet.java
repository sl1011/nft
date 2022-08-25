package com.shitouren.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


import fosun.sumpay.merchant.integration.core.request.Request;
import fosun.sumpay.merchant.integration.core.request.outer.CheckBillRequest;
import fosun.sumpay.merchant.integration.core.request.outer.CheckCardNoRequest;
import fosun.sumpay.merchant.integration.core.request.outer.GetAvaliableBanksAndBindedCardsRequest;
import fosun.sumpay.merchant.integration.core.request.outer.NewQuickPayOrderApplyRequest;
import fosun.sumpay.merchant.integration.core.request.outer.NewQuickPayVerifyCodeRequest;
import fosun.sumpay.merchant.integration.core.request.outer.OrderQueryRequest;
import fosun.sumpay.merchant.integration.core.request.outer.QuickPayMessageSendRequest;
import fosun.sumpay.merchant.integration.core.request.outer.QuickPayRequest;
import fosun.sumpay.merchant.integration.core.request.outer.RefundQueryRequest;
import fosun.sumpay.merchant.integration.core.request.outer.RefundRequest;
import fosun.sumpay.merchant.integration.core.request.outer.RemoveCardRequest;
import fosun.sumpay.merchant.integration.core.service.SumpayService;
import fosun.sumpay.merchant.integration.core.service.SumpayServiceImpl;

/**
 * Servlet implementation class DemoServlet
 */
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEST_URL = "http://124.160.28.138:8180/entrance/gateway.htm";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("interType");

		Request request2 = new Request();
		if ("pay".equals(type)) {
			this.processPay(request, request2);
		} else if ("sendMsg".equals(type)) {
			this.processSendMsg(request, request2);
		} else if ("checkBill".equals(type)) {
			this.processCheckBill(request, request2);
		} else if ("refundQuery".equals(type)) {
			this.processRefundQuery(request, request2);
		} else if ("refund".equals(type)) {
			this.processRefund(request, request2);
		} else if ("tradeQuery".equals(type)) {
			this.processTradeQuery(request, request2);
		} else if ("removeCard".equals(type)) {
			this.processRemoveCard(request, request2);
		} else if ("checkCard".equals(type)) {
			this.processCheckCard(request, request2);
		} else if ("avaliBanks".equals(type)) {
			this.processAvaliBanks(request, request2);
		}

		// 请求的参数部分自行组装，可以用纯服务端，收银台会返回url，打开就是我们的收银台
		// 但是必须用这个SumpayService服务，里面有加密和签名的，建议直接采用我们给的工具包
		SumpayService ss = new SumpayServiceImpl();
		Map<String, String> res = ss.execute(request2);
		if (type.startsWith("cashier") && "000000".equals(res.get("resp_code"))) {
			response.sendRedirect(res.get("redirect_url"));
		} else {
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().println(JSON.toJSONString(res));
		}
	}

	private void processRemoveCard(HttpServletRequest request, Request request2) {
		RemoveCardRequest req = new RemoveCardRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setBind_card_id(request.getParameter("bind_card_id"));
		req.setFormat("JSON");
		req.setMer_no(request.getParameter("mer_no"));
		req.setService("fosun.sumpay.api.quickpay.remove.card");
		req.setSub_mer_no(request.getParameter("sub_mer_no"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setUser_id(request.getParameter("user_id"));
		req.setVersion("1.0");

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processCheckCard(HttpServletRequest request, Request request2) {
		CheckCardNoRequest req = new CheckCardNoRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setCard_no(request.getParameter("card_no"));
		req.setBusiness_code(request.getParameter("business_code"));
		req.setFormat("JSON");
		req.setMer_no(request.getParameter("mer_no"));
		req.setService("fosun.sumpay.api.quickpay.check.card.no");
		req.setSub_mer_no(request.getParameter("sub_mer_no"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setVersion("1.0");

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processAvaliBanks(HttpServletRequest request, Request request2) {
		GetAvaliableBanksAndBindedCardsRequest req = new GetAvaliableBanksAndBindedCardsRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setFormat("JSON");
		req.setBusiness_code(request.getParameter("business_code"));
		req.setMer_no(request.getParameter("mer_no"));
		req.setService("fosun.sumpay.api.quickpay.avaliable.bank");
		req.setSub_mer_no(request.getParameter("sub_mer_no"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setUser_id(request.getParameter("user_id"));
		req.setVersion("1.0");

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processTradeQuery(HttpServletRequest request, Request request2) {
		OrderQueryRequest req = new OrderQueryRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setFormat("JSON");
		req.setMer_no(request.getParameter("mer_no"));
		req.setOrder_no(request.getParameter("order_no"));
		req.setService("fosun.sumpay.api.trade.order.search.merchant");
		req.setSub_mer_no(request.getParameter("sub_mer_no"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setVersion("1.0");

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processRefund(HttpServletRequest request, Request request2) {
		RefundRequest req = new RefundRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setFormat("JSON");
		req.setMer_no(request.getParameter("mer_no"));
		req.setOrder_no(request.getParameter("order_no"));
		req.setService("fosun.sumpay.api.trade.refund");
		req.setSub_mer_no(request.getParameter("sub_mer_no"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setVersion("1.0");
		req.setNotify_url(request.getParameter("notify_url"));
		req.setRefund_amt(request.getParameter("refund_amt"));
		req.setRefund_no(request.getParameter("refund_no"));
		req.setRemark(request.getParameter("remark"));

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processCheckBill(HttpServletRequest request, Request request2) {
		CheckBillRequest req = new CheckBillRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setFormat("JSON");
		req.setMer_no(request.getParameter("mer_no"));
		req.setPart_num(request.getParameter("part_num"));
		req.setService("fosun.sumpay.api.checking.checkbills");
		req.setBill_date(request.getParameter("bill_date"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setVersion("1.0");

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());

	}

	private void processRefundQuery(HttpServletRequest request, Request request2) {
		RefundQueryRequest req = new RefundQueryRequest();
		req.setApp_id(request.getParameter("mer_no"));
		req.setFormat("JSON");
		req.setMer_no(request.getParameter("mer_no"));
		req.setRefund_no(request.getParameter("refund_no"));
		req.setService("fosun.sumpay.api.trade.refund.search");
		req.setSub_mer_no(request.getParameter("sub_mer_no"));
		req.setTerminal_type(request.getParameter("terminal_type"));
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		req.setVersion("1.0");

		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(request.getParameter("domain"));
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processPay(HttpServletRequest request, Request request2) {

		//String sub_merid = request.getParameter("subMerId").trim();
		// String trade_code =request.getParameter("tradeCode").trim();
		String mer_id = request.getParameter("merId").trim();
		String order_no = request.getParameter("orderNo").trim();
		String terminal_type = request.getParameter("terminal_type").trim();
		String domain = request.getParameter("domain").trim();
		//String token = request.getParameter("token").trim();
		NewQuickPayVerifyCodeRequest req = new NewQuickPayVerifyCodeRequest();
		req.setMer_no(mer_id);
		req.setApp_id(mer_id);
		//req.setSub_mer_no(sub_merid);
		//req.setToken(token);//申请支付时返回 result_type为1时必填，为外部签约同步回调返回
		req.setOrder_no(order_no);
		req.setVerify_code(request.getParameter("verify_code").trim());//申请支付时返回 result_type为0时必填

		req.setTerminal_type(terminal_type);
		req.setService("fosun.sumpay.api.quickpay.submit.pay");
		req.setVersion("1.0");
		req.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

		// 建立请求
		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(domain);
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	private void processSendMsg(HttpServletRequest request, Request request2) {
		// 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		String sub_merid = request.getParameter("subMerId").trim();
		// String trade_code =request.getParameter("tradeCode").trim();
		String mer_id = request.getParameter("merId").trim();
		String user_id = request.getParameter("userId").trim();

		String order_no = request.getParameter("orderNo").trim();

		String order_amt = request.getParameter("orderAmt").trim();

		// String cur_type =request.getParameter("curType").trim();

		String terminal_type = request.getParameter("terminal_type").trim();

		String domain = request.getParameter("domain").trim();
		String bind_card_id = request.getParameter("bind_card_id").trim();
		String realname = request.getParameter("realname").trim();
		String id_card_no = request.getParameter("idCardNo").trim();

		NewQuickPayOrderApplyRequest req = new NewQuickPayOrderApplyRequest();
		if (!"".endsWith(bind_card_id)) {
			req.setBind_card_id(bind_card_id);
		} else {
			req.setCard_no(request.getParameter("card_no").trim());
			req.setBank_code(request.getParameter("bank_code").trim());
			String cardType = request.getParameter("card_type").trim();
			req.setCard_type(cardType);
			if ("1".equals(cardType)) {
				req.setCvv(request.getParameter("cvv").trim());
				req.setValid_year(request.getParameter("valid_year").trim());
				req.setValid_month(request.getParameter("valid_month").trim());
			}
			req.setMobile_no(request.getParameter("mobile_no").trim());
		}
		req.setMer_no(mer_id);
		req.setApp_id(mer_id);
		req.setTrade_code("T0002");
		req.setSub_mer_no(sub_merid);
		req.setUser_id(user_id);
		req.setOrder_no(order_no);
		req.setOrder_amount(order_amt);
		req.setTerminal_type(terminal_type);
		req.setFormat("JSON");
		req.setService("fosun.sumpay.api.quickpay.new.order.apply");
		req.setVersion("1.0");
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		req.setTimestamp(timestamp);
		req.setOrder_time(timestamp);
		req.setNeed_notify("1");
		req.setNotify_url("https://www.123.com");
		req.setCurrency("CNY");
		req.setGoods_name("jiangtest");
		req.setGoods_num("1");
		req.setGoods_type("2");
		req.setId_type("1");
		try {
			req.setRealname(new String(realname.getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setId_no(id_card_no);
		req.setUser_ip_addr("127.0.0.1");
		req.setFree_mark("0");// 0-普通模式；1-仅支付免短信模式
//		req.setShare_benefit_flag("1");//非分润商户不需要此参数
//		req.setShare_benefit_exp("{\"share_type\":\"1\",\"prior\":\"1\",\"benefit_bean_list\":[{\"mer_no\":\"s100000040\",\"share_type\":\"1\",\"prior\":\"2\",\"amount\":\"1.25\"}]}");

		// 建立请求
		request2.setCharset("UTF-8");// 取jsp的请求编码
		request2.setContent(JSON.toJSONString(req)); // 业务参数的json字段
		request2.setPassword("sumpay"); //
		request2.setPrivateKeyPath(request.getServletContext().getRealPath("") + "/yixuntiankong.pfx");
		request2.setPublicKeyPath(request.getServletContext().getRealPath("") + "/yixun.cer");
		request2.setUrl(TEST_URL);
		request2.setDomain(domain);
		request2.setAesEncodedWords(req.getAesEncodedWords());
		request2.setBase64EncodedWords(req.getBase64EncodedWords());
		request2.setCharsetChangeWords(req.getCharsetChangeWords());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
