# Problem statement

Find all pods with Evicted

```bash
$ cat pods.txt | grep Evicted  | awk '{ print $1 }'
```