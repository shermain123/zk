<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>

<html lang="zh-cn">
<head>
<title>密码重置</title>
<script type="text/javascript" src="${ctx }/js/user/resetUserPsd.js"></script>

</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<form class="layui-form tip_form" id="reset_User_form" onsubmit="return false;">
				<input type="hidden" id="id" name="id" value="${user.id}" />
				<div class="padding_div" >
					<fieldset class="layui-elem-field">
						<div class="tab_s layui-field-box" >
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label">用户名：</label>
									<div class="layui-input-inline">
										<input type="text" id="userCode" name="userCode"
											readonly="readonly" style="border: none;" maxlength="100"
											autocomplete="off" class="layui-input" value="${user.userCode}" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label">姓名：</label>
									<div class="layui-input-inline">
										<input type="text" id="userName" name="userName"
											readonly="readonly" style="border: none;" maxlength="100"
											autocomplete="off" class="layui-input" value="${user.userName}" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;密码：</label>
									<div class="layui-input-inline">
										<input type="password" id="passWord" name="passWord"
											lay-verify="passWord" maxlength="100" autocomplete="off"
											class="layui-input" />
									</div>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-inline">
									<label class="layui-form-label"><span style='color: red;'>*</span>&nbsp;&nbsp;确认密码：</label>
									<div class="layui-input-inline">
										<input type="password" id="rePassWord" name="rePassWord"
											lay-verify="rePassWord" maxlength="100" autocomplete="off"
											class="layui-input" />
									</div>
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<div class="layui-form-item">
					<div class="layui-block text_align_right">
						<button class="layui-btn layui-btn-normal layui-btn-small" lay-submit="" lay-filter="save">保存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
