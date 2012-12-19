stage { 'packages':
  before => Stage['main']
}

stage { 'last':
  require => Stage['main']
}

class {
    'packages':  stage => packages;
    'appserver': stage => main;
    'last':      stage => last;
}
