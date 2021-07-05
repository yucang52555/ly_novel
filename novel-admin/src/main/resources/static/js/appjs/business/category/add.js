$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/category/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			workDirection : {
				required : true
			},
			name : {
				required : true
			}/*,
			sort : {
				sort : true
			}*/
		},
		messages : {
			workDirection : {
				required : icon + "请选择作品方向"
			},
			name : {
				required : icon + "请输入类别名称"
			}/*,
			sort : {
				required : icon + "请输入排序号"
			}*/
		}
	})
}