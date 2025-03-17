#!/bin/bash

# 引数チェック
if [ -z "$1" ]; then
    echo "Usage: $0 <VPC Endpoint Service ID>"
    exit 1
fi

# 引数から VPC エンドポイントサービス ID を取得
SERVICE_ID="$1"

# VPC エンドポイント接続リクエストを取得
VPC_ENDPOINT_IDS=$(aws ec2 describe-vpc-endpoint-connections \
    --filters "Name=service-id,Values=$SERVICE_ID" \
    --query "VpcEndpointConnections[*].VpcEndpointId" \
    --output text)

# VPC エンドポイント接続リクエストがない場合
if [ -z "$VPC_ENDPOINT_IDS" ]; then
    echo "No VPC Endpoint connection requests for Service ID: $SERVICE_ID"
    exit 0
fi

# 接続リクエストを却下
echo "Rejecting VPC Endpoint connections for Service ID: $SERVICE_ID"
aws ec2 reject-vpc-endpoint-connections --service-id "$SERVICE_ID" --vpc-endpoint-ids $VPC_ENDPOINT_IDS

echo "Rejected VPC Endpoint connections: $VPC_ENDPOINT_IDS"
