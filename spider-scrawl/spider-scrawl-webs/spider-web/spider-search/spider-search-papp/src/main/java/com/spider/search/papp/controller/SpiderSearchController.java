package com.spider.search.papp.controller;

import com.mongodb.client.MongoDatabase;
import com.spider.search.service.api.mongo.*;
import com.spider.search.service.dto.InputDataServiceDTO;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("spider-search/spider")
public class SpiderSearchController
{
    @Autowired
    private SpiderCalService spiderCalService;
    @Autowired
    private KeyExtractService keyExtractService;
    @Autowired
    private SummaryExtractService summaryExtractService;
    @Autowired
    private SimilarCalService similarCalService;
    @Autowired
    private HotsCalService hotsCalService;
    @Autowired
    private ReverseIndexCalService reverseIndexCalService;
    @Autowired
    private InputDataService inputDataService;
    @Autowired
    private SoService soService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value={"spiderCal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void spiderCal()
    {
        try
        {
            System.out.println("爬虫计算开始---------------------------------------------");
            this.spiderCalService.cal();
            System.out.println("爬虫计算结束---------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
    }

    @RequestMapping(value={"keyExtractCal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void keyExtractCal()
    {
        try
        {
            System.out.println("关键字计算开始---------------------------------------------");
            this.keyExtractService.cal();
            System.out.println("关键字计算结束---------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
    }

    @RequestMapping(value={"summaryExtractCal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void summaryExtractCal()
    {
        try
        {
            System.out.println("摘要提取计算开始---------------------------------------------");
            this.summaryExtractService.cal();
            System.out.println("摘要提取计算结束---------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
    }

    @RequestMapping(value={"reverseIndexCal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void reverseIndexCal()
    {
        try
        {
            System.out.println("倒序索引计算开始---------------------------------------------");
            this.reverseIndexCalService.cal();
            System.out.println("倒序索引计算结束---------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
    }

    @RequestMapping(value={"similarCal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void similarCal()
    {
        try
        {
            System.out.println("相似度计算开始---------------------------------------------");
            this.similarCalService.cal();
            System.out.println("相似度计算结束---------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
    }

    @RequestMapping(value={"hotsCal"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public void hotsCal()
    {
        try
        {
            System.out.println("热度计算开始---------------------------------------------");
            this.hotsCalService.cal();
            System.out.println("热度计算结束---------------------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
    }

    @RequestMapping(value = "/getList", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public List<InputDataServiceDTO> hasPayRecord(@RequestParam(required = false) Integer pageNum){
        System.out.println("----------------------------getList-------------------------------------");
        System.out.println("pageNum"+pageNum);
        List<InputDataServiceDTO> inputDataServiceDTOList = new ArrayList<>();
        InputDataServiceDTO inputDataServiceDTO = new InputDataServiceDTO();
        inputDataServiceDTO.setId("1");
        inputDataServiceDTOList.add(inputDataServiceDTO);
        return inputDataServiceDTOList;
    }

    @RequestMapping(value={"list"}, produces={"application/json;charset=utf-8"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public List<InputDataServiceDTO> list(ServletRequest request, HttpServletResponse response)
    {
        System.out.println("测试list-------------------------------------------------------------");
        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        MongoDatabase mongoDatabase = mongoConnUtil.initConn();
        this.inputDataService.setDatabase(mongoDatabase);
        this.soService.setDatabase(mongoDatabase);

        List<InputDataServiceDTO> list04 = new ArrayList();
        try
        {
            String keyWord = "";
            String pageNum = "";
            System.out.println("in keyWord:" + request.getParameter("keyWord"));
            if (request.getParameter("keyWord") != null) {
                keyWord = request.getParameter("keyWord");
            }
            if (request.getParameter("pageNum") != null) {
                pageNum = request.getParameter("pageNum");
            }
            System.out.println("111keyWord:" + keyWord);
            System.out.println("pageNum:" + pageNum);
            Integer offSet = Integer.valueOf(0);
            if (StringUtils.isNotBlank(pageNum)) {
                offSet = Integer.valueOf(Integer.parseInt(pageNum));
            }
            int icount;
            if (StringUtils.isBlank(keyWord))
            {
                List<InputDataServiceDTO> list03 = this.inputDataService.searchByKeyWord(keyWord, offSet);
                for (icount = 0; icount < list03.size(); icount++)
                {
                    InputDataServiceDTO fundInputData = new InputDataServiceDTO();
                    fundInputData = (InputDataServiceDTO)list03.get(icount);
                    String url = fundInputData.getUrl();
                    if (!url.contains("www")) {
                        url = "http://www." + url.substring(7, url.length());
                    }
                    fundInputData.setUrl(url);
                    list04.add(fundInputData);
                }
            }
            else
            {
                List<Document> list03 = this.soService.soBySentence(keyWord);
                if ((null != list03) && (list03.size() >= 0)) {
                    for (Document doc : list03)
                    {
                        InputDataServiceDTO inputData = new InputDataServiceDTO();
                        inputData.setTitle(doc.getString("title"));
                        inputData.setSummary(doc.getString("summary"));
                        inputData.setUrl(doc.getString("url"));
                        inputData.setUrlId(doc.getString("urlId"));
                        inputData.setImageId(doc.getString("imageId"));
                        list04.add(inputData);
                    }
                } else {
                    list04 = null;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            mongoConnUtil.connClose();
        }
        return list04;
    }

    @RequestMapping(value={"soImage"}, method={org.springframework.web.bind.annotation.RequestMethod.GET, org.springframework.web.bind.annotation.RequestMethod.POST})
    public void doPostImage(@RequestParam Map param, HttpServletResponse response)
            throws ServletException, IOException
    {
        MongoConnUtil mongoConnUtil = new MongoConnUtil();
        MongoDatabase mongoDatabase = mongoConnUtil.initConn();
        this.imageService.setDatabase(mongoDatabase);

        String imageId = null;
        String imagePath = null;
        if ((null != param.get("imageId")) && (StringUtils.isNotEmpty(String.valueOf(param.get("imageId")))))
        {
            imageId = String.valueOf(param.get("imageId"));
            Document doc = new Document();
            doc.put("imageId", imageId);
            Document doc01 = this.imageService.findOne(doc);
            if ((null != doc01) && (StringUtils.isNotEmpty(String.valueOf(doc01.get("imagePath"))))) {
                imagePath = String.valueOf(doc01.get("imagePath"));
            }
            if (null != imagePath)
            {
                response.setContentType("text/html; charset=UTF-8");
                response.setContentType("image/jpeg");
                FileInputStream fis = null;
                OutputStream os = null;
                try
                {
                    fis = new FileInputStream(imagePath);
                    os = response.getOutputStream();
                    int count = 0;
                    byte[] buffer = new byte[10000];
                    while ((count = fis.read(buffer)) != -1) {
                        os.write(buffer, 0, count);
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    try
                    {
                        if (os != null) {
                            os.close();
                        }
                        if (fis != null) {
                            fis.close();
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
