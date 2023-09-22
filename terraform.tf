provider "aws" {
  region = "sa-east-1" 
}

resource "aws_eks_cluster" "eks_cluster" {
  name     = "cred-reneg-cluster"
  role_arn = aws_iam_role.eks_cluster_role.arn

  vpc_config {
    subnet_ids = [
      aws_subnet.private_subnet1.id,
      aws_subnet.private_subnet2.id
    ]
  }
}
resource "aws_eks_node_group" "eks_node_group" {
  cluster_name    = aws_eks_cluster.eks_cluster.name
  node_group_name = "eks-node-group"

  scaling_config {
    desired_size = 2
    min_size     = 1
    max_size     = 3
  }

  remote_access {
    ec2_ssh_key = "my-ssh-key"
    source_security_group_ids = [aws_security_group.eks_cluster_sg.id]
  }

  subnet_ids = [
    aws_subnet.private_subnet1.id,
    aws_subnet.private_subnet2.id
  ]
}

resource "aws_sns_topic" "cobranca_email_topic" {
  name = "cobranca-email-topic"
}

resource "aws_db_instance" "cobranca_db" {
  engine               = "mysql"
  instance_class       = "db.t3.micro"
  allocated_storage    = 20
  storage_type         = "gp2"
  db_subnet_group_name = aws_db_subnet_group.db_subnet_group.name
}

resource "aws_db_instance" "pagamento_db" {
  engine               = "mysql"
  instance_class       = "db.t3.micro"
  allocated_storage    = 20
  storage_type         = "gp2"
  db_subnet_group_name = aws_db_subnet_group.db_subnet_group.name
}

resource "aws_db_instance" "renegociacao_db" {
  engine               = "mysql"
  instance_class       = "db.t3.micro"
  allocated_storage    = 20
  storage_type         = "gp2"
  db_subnet_group_name = aws_db_subnet_group.db_subnet_group.name
}

resource "aws_lambda_function" "lambda_function" {
  function_name = "my-lambda-function"
  handler       = "index.handler"
  runtime       = "nodejs14.x"
  role          = aws_iam_role.lambda_role.arn

}

resource "aws_elasticache_cluster" "elasticache_cluster" {
  cluster_id           = "cred-reneg-cache-cluster"
  engine               = "redis"
  node_type            = "cache.t2.micro"
  num_cache_nodes      = 1
  subnet_group_name    = aws_elasticache_subnet_group.elasticache_subnet_group.name
  security_group_ids   = [aws_security_group.elasticache_sg.id]
}

resource "aws_api_gateway_rest_api" "api_gateway" {
  name        = "api-gateway"
  description = "API Gateway para os microsservicos de credito e renegociacao"
}
resource "aws_lb" "network_load_balancer" {
  name               = "network-load-balancer"
  internal           = false
  load_balancer_type = "network"
  subnets            = [
    aws_subnet.private_subnet1.id,
    aws_subnet.private_subnet2.id
  ]
}

resource "aws_iam_role" "eks_cluster_role" {
  name = "eks-cluster-role"
}

resource "aws_iam_role" "lambda_role" {
  name = "lambda-role"
}

resource "aws_subnet" "public_subnet1" {
  # public subnet AZ1
}

resource "aws_subnet" "public_subnet2" {
  # public subnet AZ2
}

resource "aws_subnet" "private_subnet1" {
  # private subnet AZ1
}

resource "aws_subnet" "private_subnet2" {
  # private subnet AZ2
}
