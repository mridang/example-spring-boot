# AWS Copilot Sample Service

This repository contains a sample Spring Boot application that is deployed on to ECS via AWS Copilot.

The application mode is a "Load Balanced Web Service" and was chosen based on the recommedations in this guide. https://aws.github.io/copilot-cli/docs/concepts/services/

## Developing

**Install the Copilot CLI**

You'll need to have the AWS Copilot CLI installed prior to proceeding. https://aws.github.io/copilot-cli/docs/overview/

The guide will explain how you should install the CLI. For OSX, it is a simple `brew install aws/tap/copilot-cli`.

## Building

You also need JDK11 installed and `JAVA_HOME` pointing to JDK11.

Running the following should run both the tests and also generate all the build artifacts.

```shell
./gradlew clean build
```

## Testing

The project uses the Spring Boot plugin for Gradle. In order to run the application locally, you can use:

```bash
./gradlew bootRun
```

### Checking the Docker container

```bash
./gradlew build
docker build -t aws-copilot-sample-service/app .
docker run -d -p 80:80 aws-copilot-sample-service/app
curl http://localhost:80/actuator/health
{"status":"UP"}
```

## Deploying

Deploying the service is a quick one liner. Deployments are rolling and therefore do take a few minutes.

```bash
copilot svc deploy --name backend --app searchy --env staging
```

⚠️ The Copilot CLI can be iffy at times and deployments do get stuck. If this happens, simply cancel the deploy and rerun the deploy with the `--force` flag.

##### Example

```bash
$ copilot svc deploy --name backend --app searchy --env staging
[+] Building 3.5s (8/8) FINISHED
Login Succeeded
The push refers to repository [123456789012.dkr.ecr.ap-northeast-1.amazonaws.com/searchy/backend]
✔ Proposing infrastructure changes for stack sample-app-staging-backend
- Creating the infrastructure for stack sample-app-staging-backend
  - Service discovery for your services to communicate within the VPC
  - Update your environment's shared resources
  - An IAM Role for the Fargate agent to make AWS API calls on your behalf
  - A CloudWatch log group to hold your service logs
  - An ECS service to run and maintain your tasks in the environment cluster
  - An ECS task definition to group your containers and run them on ECS
  - An IAM role to control permissions for the containers in your tasks
✔ Deployed backend.
```

### Creating another environment

When deploying this to a new account, you must create an environment. Begin 
by listing all existing environments using the `copilt env ls` command.

##### Example
```shell
$ copilot env ls
production
```

In this case there are already one existing environment. In the event 
that you need to create a new one called stating, you can do so
using the `copilot env init --name staging` command.

```bash
$ copilot env init --name staging
Which credentials would you like to use to create staging? [profile default]
Would you like to use the default configuration for a new environment?
    - A new VPC with 2 AZs, 2 public subnets and 2 private subnets
    - A new ECS Cluster
    - New IAM Roles to manage services and jobs in your environment
 Yes, use default.
✔ Linked account 123456789012 and region ap-northeast-1 to application sample-app.
✔ Proposing infrastructure changes for the sample-app-staging environment.
✔ Created environment staging in region ap-northeast-1 under application sample-app.
```

### Updating deployment configuration

The configuration for the environment is stored in the `copilot/search/manifest.yml` file.

This file contains all the necessary information for the deployed service.

##### Example

```yaml
# The manifest for the "searchy" service.
# Read the full specification for the "Load Balanced Web Service" type at:
#  https://aws.github.io/copilot-cli/docs/manifest/lb-web-service/

# Your service name will be used in naming your resources like log groups, ECS services, etc.
name: searchy
type: Load Balanced Web Service

# Distribute traffic to your service.
http:
  # Requests to this path will be forwarded to your service.
  # To match all requests you can use the "/" path.
  path: '/'
  healthcheck: '/actuator/health'

# Configuration for your containers and service.
image:
  # Docker build arguments. For additional overrides:
  #https://aws.github.io/copilot-cli/docs/manifest/lb-web-service/#image-build
  build: Dockerfile
  # Port exposed through your container to route traffic to it.
  port: 80

cpu: 256       # Number of CPU units for the task.
memory: 512    # Amount of memory in MiB used by the task.
count: 1       # Number of tasks that should be running in your service.
exec: true     # Enable running commands in your container.

# Optional fields for more advanced use-cases.
#
#variables:                    # Pass environment variables as key value pairs.
#  LOG_LEVEL: info

#secrets:                      # Pass secrets from AWS Systems Manager (SSM) Parameter Store.
#  GITHUB_TOKEN: GITHUB_TOKEN

# You can override any of the values defined above by environment.
#environments:
#  test:
#    count: 2               # Number of tasks to run for the "test" environment.

```

### Dumping CloudFormation Templates

If you want to get the CloudFormation templates that AWS Copilot generate, execute `copilot svc package` command.

```bash
copilot svc package --name backend --app searchy --env staging --output-dir ./infrastructure
copilot svc package --name web --app searchy --env staging --output-dir ./infrastructure
```

## References
- https://github.com/aws/copilot-cli
- https://aws.github.io/copilot-cli/
- https://aws.github.io/copilot-cli/community/guides/

## Authors

* Mridang Agarwalla <mridang@nosto.com>

## License

Apache 2.0