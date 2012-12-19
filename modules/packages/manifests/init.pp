class packages {

  $javaee_packages = [
    'java-1.6.0-openjdk',
    'java-1.6.0-openjdk-devel',
  ]

  package { $javaee_packages:
    ensure => installed,
  }

}
