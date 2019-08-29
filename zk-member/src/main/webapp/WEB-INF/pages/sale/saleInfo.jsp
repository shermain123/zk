<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>分片销售数据</title>
<script type="text/javascript" src="${ctx}/ECharts/echarts.min.js"></script>
</head>
<body>
	<div class="padding_div">
		<div class="nrq">
			<div id="container" style="width:100%;height:500px;">
				
			</div>
			
		</div>
	</div>
</body>
<script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);

//app.title = '';

option = {
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            crossStyle: {
                color: '#999'
            }
        }
    },
    toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data:['当年累计','目标值','环比变化']
    },
    xAxis: [
        {
            type: 'category',
            data: ['北京分公司','浙江分公司','河北分公司','海南分公司','深圳分公司','四川分公司','山东分公司',
                '山西分公司','重庆分公司','上海分公司','河南分公司','湖北分公司','广东分公司','福建分公司','贵州分公司',
                '新疆分公司','辽宁分公司','陕西分公司','安徽分公司','国群总部本部','黑龙江分公司','甘肃分公司',
                '云南分公司','天津分公司','宁夏分公司','广西分公司','内蒙古分公司','湖南分公司','江苏分公司','吉林分公司','江西分公司'],
            axisPointer: {
                type: 'shadow'
            }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '水量',
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
                formatter: '{value} ml'
            }
        },
        {
            type: 'value',
            name: '温度',
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
                formatter: '{value} %'
            }
        }
    ],
    series: [
        {
            name:'当年累计',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
        },
        {
            name:'目标值',
            type:'line',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
        },
        {
            name:'环比变化',
            type:'line',
            yAxisIndex: 1,
            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
        }
    ]
};

if(option && typeof option ==="object"){
	myChart.setOption(option,true);
}
</script>

</html>