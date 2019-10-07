package com.spider.search.papp.controller;

import com.spider.search.service.api.MyDataService;
import com.spider.search.service.dto.InputDataServiceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("spider")
public class SpiderSearchController{

    private final static Logger logger = LoggerFactory.getLogger(SpiderSearchController.class);

    @Autowired
    private MyDataService myDataService;
//
//    @Autowired
//    private InputDataService inputDataService;
//    @Autowired
//    private SoService soService;
//    @Autowired
//    private ImageService imageService;

    @RequestMapping(value={"start"}, produces={"application/json;charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public List<InputDataServiceDTO> start(ServletRequest request, HttpServletResponse response) {
        myDataService.start();
        return null;
    }

    @RequestMapping(value={"list"}, produces={"application/json;charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public List<InputDataServiceDTO> test(ServletRequest request, HttpServletResponse response){

        myDataService.getListByWordId();

        List<InputDataServiceDTO> inputDataServiceDTOList = new ArrayList<>();
        InputDataServiceDTO inputDataServiceDTO = new InputDataServiceDTO();
        inputDataServiceDTO.setId("1");
        inputDataServiceDTO.setSummary("摘要啊实打实地方");
        inputDataServiceDTO.setTitle("标题");
        inputDataServiceDTO.setHots(0.1);
        inputDataServiceDTO.setTxt("休息休息");
        inputDataServiceDTOList.add(inputDataServiceDTO);

        InputDataServiceDTO input01 = new InputDataServiceDTO();
        input01.setId("1");
        input01.setSummary("测试啊实打实地方");
        input01.setTitle("测试标题");
        input01.setTxt("惺惺惜惺惺想");
        inputDataServiceDTOList.add(input01);
        return inputDataServiceDTOList;
    }

//
//
//    @RequestMapping(value={"list"}, produces={"application/json;charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
//    @ResponseBody
//    public List<InputDataServiceDTO> list(ServletRequest request, HttpServletResponse response){
//        try {
////            Class<?> claz01 = Thread.currentThread().getContextClassLoader().loadClass("SoServiceImpl");
////            Class<?> claz03 = this.getClass().getClassLoader().loadClass("com.spider.search.service.impl.mongo.SoServiceImpl");
//            Class<?> claz02 = Thread.currentThread().getContextClassLoader().loadClass("com.spider.search.service.impl.mongo.SoServiceImpl");
//            SoService soService = (SoService)claz02.newInstance();
//            soService.soBySentence("爬虫");
//
////            logger.info("claz01:"+claz01);
//
//            logger.info("claz02:"+claz02);
////            logger.info("claz03:"+claz03);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        logger.info("测试list-------------------------------------------------------------");
//        MongoConnUtil mongoConnUtil = new MongoConnUtil();
//        MongoDatabase mongoDatabase = mongoConnUtil.initConn();
//        this.inputDataService.setDatabase(mongoDatabase);
//        this.soService.setDatabase(mongoDatabase);
//
//        List<InputDataServiceDTO> list04 = new ArrayList();
//        try{
//            String keyWord = "";
//            String pageNum = "";
//            logger.info("in keyWord:" + request.getParameter("keyWord"));
//            if (request.getParameter("keyWord") != null) {
//                keyWord = request.getParameter("keyWord");
//            }
//            if (request.getParameter("pageNum") != null) {
//                pageNum = request.getParameter("pageNum");
//            }
//            logger.info("111keyWord:" + keyWord);
//            logger.info("pageNum:" + pageNum);
//            Integer offSet = Integer.valueOf(0);
//            if (StringUtils.isNotBlank(pageNum)) {
//                offSet = Integer.valueOf(Integer.parseInt(pageNum));
//            }
//            int icount;
//            if (StringUtils.isBlank(keyWord))
//            {
//                List<InputDataServiceDTO> list03 = this.inputDataService.searchByKeyWord(keyWord, offSet);
//                for (icount = 0; icount < list03.size(); icount++)
//                {
//                    InputDataServiceDTO fundInputData = new InputDataServiceDTO();
//                    fundInputData = (InputDataServiceDTO)list03.get(icount);
//                    String url = fundInputData.getUrl();
//                    if (!url.contains("www")) {
//                        url = "http://www." + url.substring(7, url.length());
//                    }
//                    fundInputData.setUrl(url);
//                    list04.add(fundInputData);
//                }
//            }
//            else {
//                List<Document> list03 = this.soService.soBySentence(keyWord);
//                if ((null != list03) && (list03.size() >= 0)) {
//                    for (Document doc : list03)
//                    {
//                        InputDataServiceDTO inputData = new InputDataServiceDTO();
//                        inputData.setTitle(doc.getString("title"));
//                        inputData.setSummary(doc.getString("summary"));
//                        inputData.setUrl(doc.getString("url"));
//                        inputData.setUrlId(doc.getString("urlId"));
//                        inputData.setImageId(doc.getString("imageId"));
//                        list04.add(inputData);
//                    }
//                } else {
//                    list04 = null;
//                }
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            mongoConnUtil.connClose();
//        }
//        return list04;
//    }
//
//    @RequestMapping(value={"soImage"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
//    public void doPostImage(@RequestParam Map param, HttpServletResponse response)
//            throws ServletException, IOException {
//        MongoConnUtil mongoConnUtil = new MongoConnUtil();
//        MongoDatabase mongoDatabase = mongoConnUtil.initConn();
//        this.imageService.setDatabase(mongoDatabase);
//
//        String imageId = null;
//        String imagePath = null;
//        if ((null != param.get("imageId")) && (StringUtils.isNotEmpty(String.valueOf(param.get("imageId"))))) {
//            imageId = String.valueOf(param.get("imageId"));
//            Document doc = new Document();
//            doc.put("imageId", imageId);
//            Document doc01 = this.imageService.findOne(doc);
//            if ((null != doc01) && (StringUtils.isNotEmpty(String.valueOf(doc01.get("imagePath"))))) {
//                imagePath = String.valueOf(doc01.get("imagePath"));
//            }
//            if (null != imagePath) {
//                response.setContentType("text/html; charset=UTF-8");
//                response.setContentType("image/jpeg");
//                FileInputStream fis = null;
//                OutputStream os = null;
//                try{
//                    fis = new FileInputStream(imagePath);
//                    os = response.getOutputStream();
//                    int count = 0;
//                    byte[] buffer = new byte[10000];
//                    while ((count = fis.read(buffer)) != -1) {
//                        os.write(buffer, 0, count);
//                    }
//                }catch (IOException e){
//                    e.printStackTrace();
//                }finally{
//                    try{
//                        if (os != null) {
//                            os.close();
//                        }
//                        if (fis != null) {
//                            fis.close();
//                        }
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
}
