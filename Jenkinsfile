pipeline {
     agent any
     //定义环境变量
     environment {
         HARBOR_CREDS = credentials('jenkins-harbor-creds')
         BRANCH_NAME = sh(returnStdout: true,script: 'if [ "${BRANCH}" = "origin/master" ];  then echo "prod/${JOB_BASE_NAME}"; else echo "dev/${JOB_BASE_NAME}"; fi').trim()
     }
     //定义参数
   parameters {
         string(name: 'HARBOR_HOST', defaultValue: '172.26.56.3', description: 'harbor仓库地址')
         }

     stages {
         stage('Maven Build') {
             steps {
                 sh "echo harbor tag is ${env.BRANCH_NAME}"
                 sh "pwd"
                 sh "mvn clean package -Dmaven.test.skip=true -pl  common,core -am"
                 sh "docker login -u ${HARBOR_CREDS_USR} -p ${HARBOR_CREDS_PSW} ${params.HARBOR_HOST}"
                 sh "docker build -f core/Dockerfile -t ${params.HARBOR_HOST}/${env.BRANCH_NAME}:${BUILD_NUMBER} ."
                 sh "docker push ${params.HARBOR_HOST}/${env.BRANCH_NAME}:${BUILD_NUMBER}"
                 sh "docker rmi ${params.HARBOR_HOST}/${env.BRANCH_NAME}:${BUILD_NUMBER}"
             }
         }

     }
 }