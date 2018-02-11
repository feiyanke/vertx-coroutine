import io.vertx.core.buffer.Buffer
import io.vertx.core.file.*
import io.vertx.kotlin.coroutines.awaitResult

suspend fun FileSystem.acopy(from:String, to:String, option:CopyOptions = CopyOptions())
        = awaitResult<Void> { copy(from, to, it) }
suspend fun FileSystem.acopyRecursive(from: String, to: String, recursive:Boolean=true)
        = awaitResult<Void> { copyRecursive(from, to, recursive, it) }
suspend fun FileSystem.amove(from:String, to:String, option:CopyOptions = CopyOptions())
        = awaitResult<Void> { move(from, to, it) }
suspend fun FileSystem.atruncate(path:String, len:Long)
        = awaitResult<Void> { truncate(path, len, it) }
suspend fun FileSystem.achmod(path:String, perm:String)
        = awaitResult<Void> { chmod(path, perm, it) }
suspend fun FileSystem.achmodRecursive(path:String, perm:String, directoryPerm:String)
        = awaitResult<Void> { chmodRecursive(path, perm, directoryPerm, it) }
suspend fun FileSystem.achown(path:String, user:String?=null, group:String?=null)
        = awaitResult<Void> { chown(path, user, group, it) }
suspend fun FileSystem.aprops(path:String) : FileProps
        = awaitResult<FileProps> { props(path, it) }
suspend fun FileSystem.alprops(path:String) : FileProps
        = awaitResult<FileProps> { lprops(path, it) }
suspend fun FileSystem.alink(lin:String, path: String)
        = awaitResult<Void> { link(lin, path, it) }
suspend fun FileSystem.asymlink(lin:String, path: String)
        = awaitResult<Void> { symlink(lin, path, it) }
suspend fun FileSystem.aunlink(path:String)
        = awaitResult<Void> { unlink(path, it) }
suspend fun FileSystem.areadSymlink(path: String) : String
        = awaitResult { readSymlink(path, it) }
suspend fun FileSystem.adelete(path: String)
        = awaitResult<Void> { delete(path, it) }
suspend fun FileSystem.adeleteRecursive(path: String, recursive: Boolean=true)
        = awaitResult<Void> { deleteRecursive(path, recursive, it) }
suspend fun FileSystem.amkdir(path: String)
        = awaitResult<Void> { mkdir(path, it) }
suspend fun FileSystem.amkdir(path: String, perm: String)
        = awaitResult<Void> { mkdir(path, perm, it) }
suspend fun FileSystem.amkdirs(path: String)
        = awaitResult<Void> { mkdirs(path, it) }
suspend fun FileSystem.amkdirs(path: String, perm: String)
        = awaitResult<Void> { mkdirs(path, perm, it) }
suspend fun FileSystem.areadDir(path: String):List<String>
        = awaitResult { readDir(path, it) }
suspend fun FileSystem.areadDir(path: String, filter:String):List<String>
        = awaitResult { readDir(path, filter, it) }
suspend fun FileSystem.areadFile(path: String): Buffer
        = awaitResult { readFile(path, it) }
suspend fun FileSystem.awriteFile(path: String, buffer: Buffer)
        = awaitResult<Void> { writeFile(path, buffer, it) }
suspend fun FileSystem.aopen(path: String, option:OpenOptions = OpenOptions()):AsyncFile
        = awaitResult { open(path, option, it) }
suspend fun FileSystem.acreateFile(path: String)
        = awaitResult<Void> { createFile(path, it) }
suspend fun FileSystem.acreateFile(path: String, perm: String)
        = awaitResult<Void> { createFile(path, perm, it) }
suspend fun FileSystem.aexists(path: String):Boolean
        = awaitResult<Boolean> { exists(path, it) }
suspend fun FileSystem.afsProps(path: String):FileSystemProps
        = awaitResult<FileSystemProps> { fsProps(path, it) }