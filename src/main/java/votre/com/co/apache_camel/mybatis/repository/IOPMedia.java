package votre.com.co.apache_camel.mybatis.repository;

import org.apache.ibatis.annotations.Mapper;
import votre.com.co.apache_camel.mybatis.dto.CampaingRankDTO;

@Mapper
public interface IOPMedia {
    
    /**
     * Consulta el rango de campañas para una compañia
     * 
     * @param company compañia
     * @param media campaña actual
     * @return rango de campañas
     */
    CampaingRankDTO getCampaignRank();

}
