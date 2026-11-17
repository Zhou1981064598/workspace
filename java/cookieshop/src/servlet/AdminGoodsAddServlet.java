//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package servlet;

import model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(
        name = "admin_goods_add",
        urlPatterns = {"/admin/goods_add"}
)
public class AdminGoodsAddServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    public AdminGoodsAddServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> list = upload.parseRequest(request);
            Goods g = new Goods();
            Iterator var7 = list.iterator();

            while(true) {
                while(var7.hasNext()) {
                    FileItem item = (FileItem)var7.next();
                    String fileName;
                    if (item.isFormField()) {
                        fileName = item.getFieldName();
                        byte var18 = -1;
                        switch(fileName.hashCode()) {
                            case -858802731:
                                if (fileName.equals("typeid")) {
                                    var18 = 4;
                                }
                                break;
                            case 3373707:
                                if (fileName.equals("name")) {
                                    var18 = 0;
                                }
                                break;
                            case 100361836:
                                if (fileName.equals("intro")) {
                                    var18 = 2;
                                }
                                break;
                            case 106934601:
                                if (fileName.equals("price")) {
                                    var18 = 1;
                                }
                                break;
                            case 109770518:
                                if (fileName.equals("stock")) {
                                    var18 = 3;
                                }
                        }

                        switch(var18) {
                            case 0:
                                g.setName(item.getString("utf-8"));
                                break;
                            case 1:
                                g.setPrice((float)Integer.parseInt(item.getString("utf-8")));
                                break;
                            case 2:
                                g.setIntro(item.getString("utf-8"));
                                break;
                            case 3:
                                g.setStock(Integer.parseInt(item.getString("utf-8")));
                                break;
                            case 4:
                                g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                        }
                    } else if (item.getInputStream().available() > 0) {
                        fileName = item.getName();
                        fileName = fileName.substring(fileName.lastIndexOf("."));
                        fileName = "/" + (new Date()).getTime() + fileName;
                        String path = this.getServletContext().getRealPath("/picture") + fileName;
                        InputStream in = item.getInputStream();
                        FileOutputStream out = new FileOutputStream(path);
                        byte[] buffer = new byte[1024];
                        boolean var14 = false;

                        while(in.read(buffer) > 0) {
                            out.write(buffer);
                        }

                        in.close();
                        out.close();
                        item.delete();
                        String var15 = item.getFieldName();
                        byte var16 = -1;
                        switch(var15.hashCode()) {
                            case -1185250762:
                                if (var15.equals("image1")) {
                                    var16 = 1;
                                }
                                break;
                            case -1185250761:
                                if (var15.equals("image2")) {
                                    var16 = 2;
                                }
                                break;
                            case 94852023:
                                if (var15.equals("cover")) {
                                    var16 = 0;
                                }
                        }

                        switch(var16) {
                            case 0:
                                g.setCover("/picture" + fileName);
                                break;
                            case 1:
                                g.setImage1("/picture" + fileName);
                                break;
                            case 2:
                                g.setImage2("/picture" + fileName);
                        }
                    }
                }

                this.gService.insert(g);
                request.getRequestDispatcher("/admin/goods_list").forward(request, response);
                break;
            }
        } catch (FileUploadException var17) {
            var17.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
