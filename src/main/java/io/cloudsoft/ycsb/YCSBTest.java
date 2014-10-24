package io.cloudsoft.ycsb;

import java.util.List;
import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.google.common.collect.Lists;

import brooklyn.entity.basic.AbstractApplication;
import brooklyn.entity.basic.Entities;
import brooklyn.entity.basic.StartableApplication;
import brooklyn.entity.proxying.EntitySpec;
import brooklyn.launcher.BrooklynLauncher;
import brooklyn.util.CommandLineUtil;

public class YCSBTest extends AbstractApplication {

    public static String DEFAULT_LOCATION_SPEC = "AWS Virginia (us-east-1)";

    @Override
    public void init() {
        List<String> hosts = Lists.newArrayList("test01", "test02");
        List<String> workloadFiles = Lists.newArrayList("classpath://workload-testa", "classpath://workload-testb");

        Map<String, String> props = Maps.newHashMap();
        props.put("recordcount", "100000");
        props.put("measurementtype", "timeseries");

        addChild(EntitySpec.create(YCSBNode.class)
                .configure(YCSBNode.DB_HOSTNAMES, hosts)
                .configure(YCSBNode.YCSB_PROPERTIES, props)
                .configure(YCSBNode.WORKLOAD_FILES, workloadFiles));
    }

    public static void main(String[] argv) {
        List<String> args = Lists.newArrayList(argv);
        String port = CommandLineUtil.getCommandLineOption(args, "--port", "8081+");
        String location = CommandLineUtil.getCommandLineOption(args, "--location", DEFAULT_LOCATION_SPEC);

        BrooklynLauncher launcher = BrooklynLauncher.newInstance()
                .application(EntitySpec.create(StartableApplication.class, YCSBTest.class)
                        .displayName("YCSB Test"))
                .webconsolePort(port)
                .location(location)
                .start();

        Entities.dumpInfo(launcher.getApplications());
    }
}