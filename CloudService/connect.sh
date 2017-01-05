#!/usr/bin/env bash
#
# Created by Juergen Hoffmann <buddy@redhat.com>
# extended by Patrick Steiner <psteiner@redhat.com>
#

# This script builds all required docker images.

IMAGE_NAME="receiver"

function connect_image {
  IMAGE=$1
  CONTAINER_ID=$(docker ps | grep $IMAGE_NAME | cut -c1-13 )
  PID=$(docker inspect --format '{{ .State.Pid }}' $CONTAINER_ID)

  echo "Connecting $IMAGE_NAME with CONTAINER_ID <$CONTAINER_ID> and PID <$PID>"

  sudo nsenter -m -u -n -i -p -t $PID
}

connect_image
