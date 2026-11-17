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
        name = "admin_goods_edit",
        urlPatterns = {"/admin/goods_edit"}
)
public class AdminGoodsEditServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();

    public AdminGoodsEditServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> list = upload.parseRequest(request);
            Goods g = new Goods();
            int pageNumber = 1;
            int type = 0;
            Iterator var9 = list.iterator();

            while(true) {
                while(var9.hasNext()) {
                    FileItem item = (FileItem)var9.next();
                    String fileName;
                    if (item.isFormField()) {
                        fileName = item.getFieldName();
                        byte var20 = -1;
                        switch(fileName.hashCode()) {
                            case -1185250762:
                                if (fileName.equals("image1")) {
                                    var20 = 5;
                                }
                                break;
                            case -1185250761:
                                if (fileName.equals("image2")) {
                                    var20 = 6;
                                }
                                break;
                            case -858802731:
                                if (fileName.equals("typeid")) {
                                    var20 = 8;
                                }
                                break;
                            case 3355:
                                if (fileName.equals("id")) {
                                    var20 = 0;
                                }
                                break;
                            case 3373707:
                                if (fileName.equals("name")) {
                                    var20 = 1;
                                }
                                break;
                            case 3575610:
                                if (fileName.equals("type")) {
                                    var20 = 10;
                                }
                                break;
                            case 94852023:
                                if (fileName.equals("cover")) {
                                    var20 = 4;
                                }
                                break;
                            case 100361836:
                                if (fileName.equals("intro")) {
                                    var20 = 3;
                                }
                                break;
                            case 106934601:
                                if (fileName.equals("price")) {
                                    var20 = 2;
                                }
                                break;
                            case 109770518:
                                if (fileName.equals("stock")) {
                                    var20 = 7;
                                }
                                break;
                            case 1144767160:
                                if (fileName.equals("pageNumber")) {
                                    var20 = 9;
                                }
                        }

                        switch(var20) {
                            case 0:
                                g.setId(Integer.parseInt(item.getString("utf-8")));
                                break;
                            case 1:
                                g.setName(item.getString("utf-8"));
                                break;
                            case 2:
                                g.setPrice(Float.parseFloat(item.getString("utf-8")));
                                break;
                            case 3:
                                g.setIntro(item.getString("utf-8"));
                                break;
                            case 4:
                                g.setCover(item.getString("utf-8"));
                                break;
                            case 5:
                                g.setImage1(item.getString("utf-8"));
                                break;
                            case 6:
                                g.setImage2(item.getString("utf-8"));
                                break;
                            case 7:
                                g.setStock(Integer.parseInt(item.getString("utf-8")));
                                break;
                            case 8:
                                g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                                break;
                            case 9:
                                pageNumber = Integer.parseInt(item.getString("utf-8"));
                                break;
                            case 10:
                                type = Integer.parseInt(item.getString("utf-8"));
                        }
                    } else if (item.getInputStream().available() > 0) {
                        fileName = item.getName();
                        fileName = fileName.substring(fileName.lastIndexOf("."));
                        fileName = "/" + (new Date()).getTime() + fileName;
                        String path = this.getServletContext().getRealPath("/picture") + fileName;
                        InputStream in = item.getInputStream();
                        FileOutputStream out = new FileOutputStream(path);
                        byte[] buffer = new byte[1024];
                        boolean var16 = false;

                        while(in.read(buffer) > 0) {
                            out.write(buffer);
                        }

                        in.close();
                        out.close();
                        item.delete();
                        String var17 = item.getFieldName();
                        byte var18 = -1;
                        switch(var17.hashCode()) {
                            case -1185250762:
                                if (var17.equals("image1")) {
                                    var18 = 1;
                                }
                                break;
                            case -1185250761:
                                if (var17.equals("image2")) {
                                    var18 = 2;
                                }
                                break;
                            case 94852023:
                                if (var17.equals("cover")) {
                                    var18 = 0;
                                }
                        }

                        switch(var18) {
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

                this.gService.update(g);
                request.getRequestDispatcher("/admin/goods_list?pageNumber=" + pageNumber + "&type=" + type).forward(request, response);
                break;
            }
        } catch (FileUploadException var19) {
            var19.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
