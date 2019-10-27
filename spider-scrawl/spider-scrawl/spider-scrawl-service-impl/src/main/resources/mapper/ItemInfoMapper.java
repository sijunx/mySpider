package mapper;

import mapper.ItemInfo;

public interface ItemInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    ItemInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);
}