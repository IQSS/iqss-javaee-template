# Template for IQSS Java EE Applications

## Background

Some of the [technology products][] from the Institute for Quantitative Social Science (IQSS) at Harvard University are built on [Java EE][].

[technology products]: http://www.iq.harvard.edu/products
[Java EE]: http://en.wikipedia.org/wiki/Java_Platform,_Enterprise_Edition

The aim of this git repo is to gather common components...

- Glassfish: <http://en.wikipedia.org/wiki/GlassFish>
- ICEfaces: <http://en.wikipedia.org/wiki/ICEfaces>

... into a testable framework using Vagrant: http://vagrantup.com 

For now, the "hello1" demo app from the [Java EE 6 tutorial][] is deployed, but the idea is to have the app exercise ICEfaces and components as well.

[Java EE 6 tutorial]: http://docs.oracle.com/javaee/6/tutorial/doc/

## Usage

First, we'll build app in NetBeans. Then, we'll deploy it locally and later to our Vagrant VM.

### Set up the NetBeans project and deploy hello1 on local GlassFish

In NetBeans, click Team, then Git, then Clone. 

For the Repository URL: enter https://github.com/iqss/iqss-javaee-template.git (for read-only access, for now) and click Next.

Select "master" as the remote branch and click Next.

Parent Directory should be "/Users/USERNAME/NetBeansProjects" or similar.

Clone Name should be "iqss-javaee-template"

Click Finish.

When you build and run the project, you should be able to access http://localhost:8080/hello1 on the GlassFish instance running locally on your development workstation.

### Deploy the hello1 app to GlassFish running on Vagrant

Next, we'll deploy the same `hello1.war` to GlassFish running on a VM we'll create with Vagrant.

    cd ~/NetBeansProjects/iqss-javaee-template
    vagrant up

`vagrant up` will take some time to complete because it has to download and install GlassFish on the VM.

When `vagrant up` returns, "hello1" app should be accessible on the instance of GlassFish running on the VM at http://localhost:9000/hello1
