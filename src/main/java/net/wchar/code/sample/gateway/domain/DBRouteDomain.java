package net.wchar.code.sample.gateway.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(DBRouteDomain.TABLE_NAME)
public class DBRouteDomain {

    public static final String TABLE_NAME = "sys_route";

    //主键自动增加
    @Id
    private Long id;

    //要代理的地址
    @Column(value = "proxy_address")
    private String proxyAddress;


    //请求前缀
    @Column(value = "location")
    private String location;

    //strip_prefix
    @Column(value = "strip_prefix")
    private Integer stripPrefix;
}
