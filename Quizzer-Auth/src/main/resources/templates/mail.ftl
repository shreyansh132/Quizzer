<!DOCTYPE html>
<html>
<head>
</head>
<body style="font-family: Verdana, sans-serif;">
<div style="background-color: #6600cc;
   text-align: center; height: 40px;
   line-height: 40px;">
    <h2 style="font-weight:normal;
        font-size:25px; color: #ffffff;">
        Welcome to Quizzer</h2>
</div>

<h4>Hello ${recipientName},</h4>
<p>${emailContent}</p>
<div style="background-color: #6600cc; height:45px; position:relative;">
<div style="position:absolute; padding:2px; padding-left:5px;">
    <h5 style="font-weight:normal; font-size:15px; margin:0; padding:0; color: #ffffff;">Regards</h5>
    <h5 style="font-weight:normal; font-size:12px; margin:0; padding:0; color: #ffffff;">${senderName}</h5>
</div>
   </div>
</body>
</html>