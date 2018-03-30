#!/bin/bash
rsync -auvz -e "ssh" /build/docs/ rsandor@atria.cs.odu.edu:./
rsync -auvz -e "ssh" /reports/ rsandor@atria.cs.odu.edu:./
