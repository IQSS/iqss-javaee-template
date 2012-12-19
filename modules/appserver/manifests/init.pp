class appserver {

  file { '/root/glassfish-answerfile':
    source => 'puppet:///modules/bucket/root/glassfish-answerfile',
    owner  => 'root',
    group  => 'root',
    mode   => '0600',
  }

  file { '/root/glassfish-install.sh':
    source => 'puppet:///modules/bucket/root/glassfish-install.sh',
    owner  => 'root',
    group  => 'root',
    mode   => '0600',
    require => File['/root/glassfish-answerfile'],
  }

  exec { "install_glassfish":
    command => "/bin/bash /root/glassfish-install.sh",
    onlyif  => "/usr/bin/test ! -e /opt/glassfish",
    require => File['/root/glassfish-install.sh'],
  }

  file { '/etc/sysconfig/iptables':
    source => 'puppet:///modules/bucket/etc/sysconfig/iptables',
    owner  => 'root',
    group  => 'root',
    mode   => '0600',
  }

  service { 'iptables':
    ensure    => running,
    enable    => true,
    subscribe => File['/etc/sysconfig/iptables'],
  }

}
