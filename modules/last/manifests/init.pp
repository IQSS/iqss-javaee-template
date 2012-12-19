class last {

  exec {'start-domain':
    command => '/opt/glassfish/bin/asadmin start-domain',
    onlyif  => "/opt/glassfish/bin/asadmin list-domains | grep -c 'domain1 not running'",
  }

  exec {'deploy-app-hello1':
    command => '/opt/glassfish/bin/asadmin deploy /examples/web/hello1/target/hello1.war',
    onlyif  => "/opt/glassfish/bin/asadmin list-applications | grep -c 'Nothing to list'",
    require => Exec['start-domain'],
  }

}
