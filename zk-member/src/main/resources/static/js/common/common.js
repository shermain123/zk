  
$(function(){
	//判断当前字符串是否以str结束
	if (typeof String.prototype.endsWith != 'function') {
		String.prototype.endsWith = function (str){
			return this.slice(-str.length) == str;
		};
	}
});

var DataDeal = {
	//将从form中通过$('#form').serialize()获取的值转成json  
	//isLegal=1&memberLongname=1&memberName=2&status=1&orgCode=12&registernumber=13&socialCreditCode=14
	formStrToJsonStr: function (data) {  
		data = data.replace(/&/g,"\",\"");  
		data = data.replace(/=/g,"\":\"");  
		data = "{\"" + data + "\"}";  
		return data;  
	},
	fromJsonStrToJsonObj: function(data){
		return JSON.parse(data);
	}	
}

/*
 * 验证是否是数字
 */
function testNumber(str) {
	var reg = /^[0-9]*$/;
	return reg.test(str);
}

function testDouble(str) {
	var reg = /^[0-9]+([.]{1}[0-9]+){0,1}$/;
	return reg.test(str);
}

//所有数字 正整数 0 负整数 正小数  负小数
function testFullDouble(str){
	var reg = /^(-?\d+)(\.\d+)?$/;
	return reg.test(str);
}

//正数 正整数 0 正小数
function testPosiDouble(str){
	var reg = /^(\d+)(\.\d+)?$/;
	return reg.test(str);
}

//负数 负整数 0 负小数
function testNegDouble(str){
	var reg = /^(-\d+)(\.\d+)?$/;
	return reg.test(str);
}

//正整数 0 
function testPosiNumber(str){
	var reg = /^(\d+)$/;
	return reg.test(str);
}

//负整数 0
function testNegNumber(str){
	var reg = /^(-\d+)?$/;
	return reg.test(str);
}

// 拆分资源树
function convert(rows) {
	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId)
				return true;
		}
		return false;
	}
	var nodes = [];
	// get the top level nodes
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId)) {
			nodes.push({
				id : row.id,
				text : row.name,
				attributes : {
					dicType : row.dicType,
					flag: row.flag,
					parentId: row.parentId
				}
			});
		}
	}
	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	while (toDo.length) {
		var node = toDo.shift(); // the parent node
		// get the children nodes
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.parentId == node.id) {
				var child = {
					id : row.id,
					text : row.name,
					attributes : {
						url : row.url,
						isleaf : row.isleaf,
						flag: row.flag,
						parentId: row.parentId,
						dicType : row.dicType
					}
				};
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [ child ];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
};


//验证mail
function checkMail(obj) {
  var emailReg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
  if (obj != undefined && obj.value != '' && !emailReg.test(obj.value.trim())) {
      alert("请输入正确的Email！");
      obj.focus();
      return false;
  }
  return true;
}

//验证手机号码
function checkMobilePhone(obj) {
  var reg = /^1([3|4|5|8][0-9])\d{4,8}$/;
  if (obj != undefined && obj.value != '' && !reg.test(obj.value.trim())) {
      alert("请输入正确的mobile！");
      obj.focus();
      return false;
  }
  return true;
}

if (!Array.prototype.indexOf) {
  Array.prototype.indexOf = function (obj, start) {
      for (var i = (start || 0), j = this.length; i < j; i++) {
          if (this[i] === obj) { return i; }
      }
      return -1;
  }
}

//数字转百分比
function dn(point) {
	var str = parseFloat(point.toFixed(2)) + '%';
	return str;
}

//数字转货币四舍六入不留小数
function fn(number, places, symbol, thousand, decimal) {
	number = string(number);
	// if((number % 1)<1 && (number % 1)>0){
	places = !isNaN(places = Math.abs(places)) ? places : 0;
	// }
	symbol = symbol !== undefined ? symbol : "";
	thousand = thousand || ",";
	decimal = decimal || ".";
	var negative = number < 0 ? "-" : "", i = parseInt(number = Math.abs(
			+number || 0).toFixed(), 10)
			+ "", j = (j = i.length) > 3 ? j % 3 : 0;
	return symbol
			+ negative
			+ (j ? i.substr(0, j) + thousand : "")
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand)
			+ (places ? decimal + Math.abs(number - i).toFixed(places).slice(2)
					: "");
}

//对数据进行四舍六入判断
function string(number) {
	// 整数验证
	var num = /^-?[1-9]\d*$/;
	if (number == 0 || number == null ) {
		return 0;
	} else {
		if (num.test(number)) {
			return number;
		} else {
			// 非整数四舍六入
			var number = number.toString();
			var str = number.split(".");
			for (i = 0; i < str.length; i++) {
				if ((str[0] % 2) == 0) {
					if (str[1].length > 1) {
						for (j = 1; j < str[1].length; j++) {
							if (str[1][j] == 0) {
								return parseInt(str[0]);
							} else {
								return parseFloat(number);
							}
						}
					} else {
						return parseInt(str[0])
					}
				} else {
					return parseFloat(number);
				}
			}
		}
	}
}


//时间 （年-月-日）		
function dateToStr(dates,ymd,hms){
	//ymd显示年月日
	//hms显示时分秒
	var dateStr = ""
	if(dates != '' && dates != null){
		
		var dates = new Date(dates);
		if (ymd) {
			var year = dates.getFullYear();
			var month = dates.getMonth()+1;
			var date = dates.getDate();
			if(month<10){
				month = "0"+month;
			}
			if(date < 10){
				date = "0"+date;
			}
			dateStr = year+"-"+month+"-"+date;
			if (hms) {
				dateStr += " ";
			}
		}
		
		if (hms) {
			var hours = dates.getHours();
			var minutes = dates.getMinutes();
			var seconds = dates.getSeconds();
			if (hours < 10) {
				hours = "0" + hours;
			}
			if (minutes < 10) {
				minutes = "0" + minutes;
			}
			if (seconds < 10) {
				seconds = "0" + seconds;
			}
			dateStr += (hours + ":" + minutes + ":" + seconds);
		}
	}
	
	return dateStr;
}
