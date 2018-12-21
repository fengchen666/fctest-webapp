FROM amazonlinux:latest

ADD . /opt/app

RUN yum update -y

