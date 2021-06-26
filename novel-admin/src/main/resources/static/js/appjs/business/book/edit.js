// 以下为官方示例
$().ready(function() {
	validateRule();
	// $("#signupForm").validate();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
//	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/business/book/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			catName : {
				required : true
			},
			bookName : {
				required : true,
				minlength : 2
			},
			authorName : {
				required : true
			},
			bookDesc : {
				required : true
			},
			visitCount : {
				required : true
			}
		},
		messages : {
			catName : {
				required : icon + "请输入类别名称"
			},
			bookName : {
				required : icon + "请输入书名",
				minlength : icon + "书名必须两个字符以上"
			},
			authorName : {
				required : icon + "请输入作者名字",
			},
			bookDesc : {
				required : icon + "请输入描述信息"
			},
            visitCount : {
                required : icon + "请输入阅读次数，默认100"
            }
		}
	})
}