<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>no title</title>
</head>
<body>

<input id="upload_image" type="file" onchange="upload(this)" />
<script type="text/javascript" src="/js/jquery-3.4.1.min.js"></script>
<script>
    function upload() {
        var file = $("#upload_image")[0].files[0];
        var imgName = file.name;
        var index = imgName.lastIndexOf(".");
        if (index == -1){
            var newfile = new File([file], new Date().getTime()+".jpg",{type:"image/jpeg"});
            imgName = newfile.name;
        }

        var reg=/(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$/;
        if(!reg.test(imgName)){
            alert('请上传正确格式的图片!');
            return;
        }
        alert(imgName);
        console.log(imgName);
    }
</script>
</body>
</html>
