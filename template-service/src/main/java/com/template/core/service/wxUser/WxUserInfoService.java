package com.template.core.service.wxUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * E端微信用户用业务处理
 *
 * @author zhang.lianahi
 * @version v1.0
 * @date 2020/2/20 13:41
 */
@Slf4j
@Service
public class WxUserInfoService {

    private static final String MODEL="[E端微信用户模块]";
//    @Autowired
//    private MemberUserInfoMapper memberUserInfoMapper;

    /**
     * 分页查询列表
     *
     * @param queryDTO 查询参数
     * @return {@code ResultResponse<WxUserInfoVO>} 分页列表
     */
//    public PageResult<WxUserVO> wxUserPageList(WxUserInfoQueryDTOE queryDTO) {
//        PageHelper.startPage(queryDTO.getPageNo(), queryDTO.getPageSize(), true);
//        MemberUserInfoExample example = new MemberUserInfoExample();
//        MemberUserInfoExample.Criteria criteria = example.createCriteria();
//        criteria.andDelFlagEqualTo(false);
//        if (StringUtils.isNotBlank(queryDTO.getNickname())) {
//            criteria.andNicknameLike(queryDTO.getNickname() + "%");
//        }
//        if (StringUtils.isNotBlank(queryDTO.getMobile())) {
//            criteria.andMobileLike(queryDTO.getMobile() + "%");
//        }
//        if (StringUtils.isNotBlank(queryDTO.getCity())) {
//            criteria.andCityLike(queryDTO.getCity() + "%");
//        }
//        example.setOrderByClause("create_time DESC");
//        List<MemberUserInfo> list = memberUserInfoMapper.selectByExample(example);
//        return PageResultUtil.pageResult(list, WxUserVO.class);
//    }
//
//    /**
//     * 导出微信用户信息
//     *
//     * @return 流
//     */
//    public ByteArrayOutputStream wxUserExport(WxUserInfoQueryDTOE queryDTO) {
//        MemberUserInfoExample example = new MemberUserInfoExample();
//        MemberUserInfoExample.Criteria criteria = example.createCriteria();
//        criteria.andDelFlagEqualTo(false);
//        List<Long> ids = new ArrayList<>();
//        if(StringUtils.isNotBlank(queryDTO.getIds())){
//            ids = Arrays.stream(queryDTO.getIds().split(",")).map(s ->Long.parseLong(s.trim())).collect(Collectors.toList());
//        }
//        if(CollectionUtils.isNotEmpty(ids)){
//            criteria.andIdIn(ids);
//        }else{
//            if (StringUtils.isNotBlank(queryDTO.getNickname())) {
//                criteria.andNicknameLike(queryDTO.getNickname() + "%");
//            }
//            if (StringUtils.isNotBlank(queryDTO.getMobile())) {
//                criteria.andMobileLike(queryDTO.getMobile() + "%");
//            }
//            if (StringUtils.isNotBlank(queryDTO.getCity())) {
//                criteria.andCityLike(queryDTO.getCity() + "%");
//            }
//        }
//        example.setOrderByClause("create_time DESC");
//        List<MemberUserInfo> list = memberUserInfoMapper.selectByExample(example);
//        if(CollectionUtils.isEmpty(list)){
//            throw new ServiceException(ExceptionDef.C404,"未查询到用户信息");
//        }
//        return this.createExcel(list);
//
//    }
//
//    private ByteArrayOutputStream createExcel(List<MemberUserInfo> list) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String dateString = "";
//        XSSFWorkbook workBook = new XSSFWorkbook();
//        XSSFSheet sheet = workBook.createSheet("sheet1");
//        int rowNum = 0;
//        int colNum = 0;
//
//        List<String> areaTitle = Lists.newArrayList("序号", "昵称","性别","手机号","城市","首次登陆时间","渠道");
//
//        //设置单元格样式
//        XSSFCellStyle cellStyle = workBook.createCellStyle();
//        cellStyle.setAlignment(HorizontalAlignment.CENTER);
//        XSSFFont font = workBook.createFont();
//        font.setFontHeightInPoints((short) 16);
//        font.setColor(Font.COLOR_RED);
//        font.setBold(true);
//        cellStyle.setFont(font);
//
//        XSSFCellStyle stringCellStyle = workBook.createCellStyle();
//        XSSFDataFormat format = workBook.createDataFormat();
//        stringCellStyle.setDataFormat(format.getFormat("@"));
//
//        //设置内容单元格样式
//        XSSFCellStyle contentCellStyle = workBook.createCellStyle();
//        contentCellStyle.setAlignment(HorizontalAlignment.CENTER);
//
//        //创建posSheet的数据
//        Row row = sheet.createRow(rowNum);
//        for (int i = 0; i < areaTitle.size(); i++) {
//            sheet.setColumnWidth((short) colNum, (short) 20 * 256);
//            sheet.setDefaultColumnStyle(i, stringCellStyle);
//            Cell cell = row.createCell(colNum++);
//            cell.setCellValue(areaTitle.get(i));
//            cell.setCellStyle(cellStyle);
//            cell.setCellType(CellType.STRING);
//        }
//        int index=1;
//        for (MemberUserInfo m : list) {
//            row = sheet.createRow(++rowNum);
//            Cell cell = row.createCell(0);
//            cell.setCellValue(index);
//            cell.setCellStyle(contentCellStyle);
//            cell = row.createCell(1);
//            cell.setCellValue(StringUtils.isNotBlank(m.getNickname())?m.getNickname():"");
//            cell.setCellStyle(contentCellStyle);
//            cell = row.createCell(2);
//            String sexStr="未知";
//            if(m.getSex()!=null){
//                if(m.getSex()==1){
//                    sexStr="男";
//                }else if(m.getSex()==2){
//                    sexStr="女";
//                }
//            }
//            cell.setCellValue(sexStr);
//            cell.setCellStyle(contentCellStyle);
//            cell = row.createCell(3);
//            cell.setCellValue(StringUtils.isNotBlank(m.getMobile())?m.getMobile():"");
//            cell.setCellStyle(contentCellStyle);
//            cell = row.createCell(4);
//            cell.setCellValue(StringUtils.isNotBlank(m.getCity())?m.getCity():"");
//            cell.setCellStyle(contentCellStyle);
//            cell = row.createCell(5);
//            if(m.getCreateTime()!=null){
//                dateString = formatter.format(m.getCreateTime());
//            }
//            cell.setCellValue(dateString);
//            cell.setCellStyle(contentCellStyle);
//            cell = row.createCell(6);
//            String channelStr = "";
//            if (Objects.equals(UserChannelEnums.WECHAT_APPLET.getCode(), m.getUserChannel())) {
//                channelStr = UserChannelEnums.WECHAT_APPLET.getDesc();
//            }
//            if (Objects.equals(UserChannelEnums.DESIGNER_CARD.getCode(), m.getUserChannel())) {
//                channelStr = UserChannelEnums.DESIGNER_CARD.getDesc() + (StringUtils.isBlank(m.getDesignerName()) ? "" : "-" + m.getDesignerName());
//            }
//            cell.setCellValue(channelStr);
//            cell.setCellStyle(contentCellStyle);
//            index++;
//        }
//
//        //创建字节流
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        try {
//            workBook.write(outputStream);
//            workBook.close();
//        } catch (Exception e) {
//            log.error(MODEL+"用户信息导出excel异常",e);
//            throw new ServiceException(ExceptionDef.C9999,"用户信息导出excel异常");
//        } finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                log.error("用户信息导出excel异常", e);
//            }
//        }
//        return outputStream;
//    }
//
//    /**
//     *  获取未删除的用户
//     * @param id
//     * @return
//     * @throws ServiceException
//     */
//    public  MemberUserInfo getAssertNotDeletedById(Long id) throws ServiceException {
//        MemberUserInfo memberUserInfo = memberUserInfoMapper.selectByPrimaryKey(id);
//        if (Objects.isNull(memberUserInfo) || memberUserInfo.getDelFlag()) {
//            throw new ServiceException(ExceptionDef.DATA_NOT_EXIST, "用户不存在");
//        }
//        return memberUserInfo;
//    }

}
