# Exception命名规范
## wiki
http://192.168.60.125:8080/dokuwiki/doku.php?id=%E5%BC%82%E5%B8%B8%E9%94%99%E8%AF%AF%E7%BC%96%E7%A0%81%E8%A7%84%E8%8C%83

## 编码方法
1. 一般使用MySpiderException
2. 对于facade层特别的Exception，命名为xxx模块名称+FacadeException，如xxxMessageFacadeException
3. 每个层至多只有一个类型的Exception就可以了
