package dev.sirosh.raskatbackend.driver.docker.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class DockerCreateContainerRequestDto {
    private String hostName;
    private String domainName;
    private String user;
    private Boolean attachStdin;
    private Boolean attachStdout;
    private Boolean attachStderr;
    private Boolean tty;
    private Boolean openStdin;
    private Boolean stdinOnce;
    private List<String> env;
    private List<String> cmd;
    private String entrypoint;
    private String image;
    private Map<String, String> label;
    private String workingDir;
    private Boolean networkDisabled;
    private String macAddress;
    private Map<String, Object> exposedPorts;
    private String stopSignal;
    private Integer stopTimeout;
    private HostConfig hostConfig;

    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class HostConfig {
        private List<String> binds;
        private List<String> links;
        private Map<String, String> lxcConf;
        private Integer memory;
        private Integer memorySwap;
        private Integer memoryReservation;
        private Integer kernelMemory;
        private Integer cpuShares;
        private Integer cpuPeriod;
        private Integer cpuQuota;
        private String cpusetCpus;
        private String cpusetMems;
        private Integer blkioWeight;
        private Integer memorySwappiness;
        private Boolean oomKillDisable;
        private Integer oomScoreAdj;
        private String pidMode;
        private Integer pidsLimit;
        private Map<String, List<PortBinding>> portBindings;
        private Boolean publishAllPorts;
        private Boolean privileged;
        private Boolean readonlyRootfs;
        private List<String> dns;
        private List<String> dnsOptions;
        private List<String> dnsSearch;
        private List<String> volumesFrom;
        private List<String> capAdd;
        private List<String> capDrop;
        private List<String> groupAdd;
        private RestartPolicy restartPolicy;
        private Boolean autoRemove;
        private String networkMode;
        private List<String> devices;
        private List<String> ulimits;
        private String logConfigType;
        private Map<String, String> logConfigConfig;
        private List<String> securityOpt;
        private Map<String, String> storageOpt;
        private String cgroupParent;
        private String volumeDriver;
        private Integer shmSize;
    }

    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class PortBinding {
        private String hostPort;
    }

    @Data
    @Builder
    @RequiredArgsConstructor
    @AllArgsConstructor
    @JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
    public static class RestartPolicy {
        private String name;
        private Integer maximumRetryCount;
    }
}
