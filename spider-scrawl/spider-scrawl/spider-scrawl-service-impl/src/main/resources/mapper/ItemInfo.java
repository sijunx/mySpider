package mapper;

import java.util.Date;

public class ItemInfo {
    private Long id;

    private String itemcode;

    private String itemdesc;

    private Integer itemtype;

    private Integer itemlen;

    private Date createtime;

    private Long createuserid;

    private Date updatetime;

    private Long updateuserid;

    private Byte deleteflag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public Integer getItemtype() {
        return itemtype;
    }

    public void setItemtype(Integer itemtype) {
        this.itemtype = itemtype;
    }

    public Integer getItemlen() {
        return itemlen;
    }

    public void setItemlen(Integer itemlen) {
        this.itemlen = itemlen;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Long createuserid) {
        this.createuserid = createuserid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Long getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(Long updateuserid) {
        this.updateuserid = updateuserid;
    }

    public Byte getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Byte deleteflag) {
        this.deleteflag = deleteflag;
    }
}