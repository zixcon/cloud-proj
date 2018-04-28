#### mybatis generator 插件开发

    mybatis plugin定制化代码必须是原生的maven jar，否则比如是boot项目，那么其jar包不能被mybatis-generator-maven-plugin加载
    
#### Execution default-cli of goal org.mybatis.generator:mybatis-generator-maven-plugin:1.3.2:generate failed: Cannot instantiate object of type com
    
    mybatis plugin定制化代码（JavaTypeResolver等类）必须和maven插件在统一classloader下才能加载
     
比如：
> <plugin>
                  <groupId>org.mybatis.generator</groupId>
                  <artifactId>mybatis-generator-maven-plugin</artifactId>
                  <version>1.3.5</version>
                  <executions>
                      <execution>
                          <id>Generate MyBatis Artifacts</id>
                          <goals>
                              <goal>generate</goal>
                          </goals>
                      </execution>
                  </executions>
                  <configuration>
                      <skip>true</skip>
                      <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
                      <overwrite>true</overwrite>
                      <verbose>true</verbose>
                  </configuration>
                  <dependencies>
                      <dependency>
                          <groupId>mysql</groupId>
                          <artifactId>mysql-connector-java</artifactId>
                          <version>${mysql.version}</version>
                      </dependency>
                      <dependency>
                          <groupId>org.mybatis.generator</groupId>
                          <artifactId>mybatis-generator-core</artifactId>
                          <version>1.3.5</version>
                      </dependency>
                      <dependency>
                          <groupId>com.cloud.frame.demo.common.mybatis</groupId>
                          <artifactId>cloud-frame-demo-common-mybatis</artifactId>
                          <version>0.0.1</version>
                          <scope>system</scope>
                          <systemPath>/Users/wd/Documents/dev/git/cloud-proj/cloud-proj/frame/cloud-frame/cloud-frame-demo/cloud-frame-demo-common-mybatis/target/cloud-frame-demo-common-mybatis-0.0.1.jar</systemPath>
                      </dependency>
                  </dependencies>
    
