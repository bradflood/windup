package org.jboss.windup.rules.apps.javaee.tests;

import com.google.common.collect.Iterables;
import org.apache.commons.io.FileUtils;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.furnace.util.OperatingSystemUtils;
import org.jboss.windup.exec.WindupProcessor;
import org.jboss.windup.exec.configuration.WindupConfiguration;
import org.jboss.windup.graph.GraphContext;
import org.jboss.windup.graph.GraphContextFactory;
import org.jboss.windup.graph.model.LinkModel;
import org.jboss.windup.graph.model.ProjectModel;
import org.jboss.windup.graph.model.WindupConfigurationModel;
import org.jboss.windup.graph.model.resource.FileModel;
import org.jboss.windup.graph.service.GraphService;
import org.jboss.windup.rules.apps.javaee.AbstractTest;
import org.jboss.windup.rules.apps.javaee.model.EjbMessageDrivenModel;
import org.jboss.windup.rules.apps.javaee.model.WebXmlModel;
import org.jboss.windup.rules.apps.javaee.rules.DiscoverWebXmlRuleProvider;
import org.jboss.windup.rules.apps.javaee.rules.jboss.GenerateJBossWebDescriptorRuleProvider;
import org.jboss.windup.rules.apps.xml.model.XmlFileModel;
import org.jboss.windup.testutil.basics.WindupTestUtilMethods;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;

@RunWith(Arquillian.class)
public class DiscoverWebXmlTest extends AbstractTest
{
    @Inject
    private WindupProcessor processor;

    @Inject
    private GraphContextFactory factory;

    @Test
    public void testWebXmlMetadataExtraction() throws Exception
    {
        try (GraphContext context = factory.create())
        {
            ProjectModel pm = context.getFramed().addVertex(null, ProjectModel.class);
            pm.setName("Main Project");
            FileModel inputPath = context.getFramed().addVertex(null, FileModel.class);
            inputPath.setFilePath("src/test/resources/web-xml/");

            Path outputPath = Paths.get(FileUtils.getTempDirectory().toString(), "windup_"
                        + UUID.randomUUID().toString());
            FileUtils.deleteDirectory(outputPath.toFile());
            Files.createDirectories(outputPath);

            pm.addFileModel(inputPath);
            pm.setRootFileModel(inputPath);
            WindupConfiguration windupConfiguration = new WindupConfiguration()
                        .setGraphContext(context);
            windupConfiguration.addInputPath(Paths.get(inputPath.getFilePath()));
            windupConfiguration.setOutputDirectory(outputPath);
            processor.execute(windupConfiguration);

            GraphService<WebXmlModel> webXmlModelGraphService = new GraphService<>(context, WebXmlModel.class);
            Iterator<WebXmlModel> models = webXmlModelGraphService.findAll().iterator();
            Assert.assertTrue(models.hasNext());
            models.next();
            Assert.assertFalse(models.hasNext());
        }
    }
}
