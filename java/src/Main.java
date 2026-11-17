import qrcode from io
import BytesIO

# 输入WiFi账号和密码

        ssid = input("请输入WiFi账号：")
        password = input("请输入WiFi密码：")

        # 生成WIFI连接URL
        wifi_url = f'WIFI:T:WPA;S:{ssid};P:{password};;'

        # 生成二维码图片
        qr = qrcode.QRCode(version=1, error_correction=qrcode.constants.ERROR_CORRECT_L, box_size=10, border=4)
        qr.add_data(wifi_url)
        qr.make(fit=True)

        img = qr.make_image(fill_color="black", back_color="white")

        # 保存为PNG文件
        buffered = BytesIO()
        img.save(buffered, format="PNG")
        png_data = buffered.getvalue()

        # 将PNG文件发送给用户
        # 这里需要使用相应的微信公众号接口或第三方API来实现发送功能